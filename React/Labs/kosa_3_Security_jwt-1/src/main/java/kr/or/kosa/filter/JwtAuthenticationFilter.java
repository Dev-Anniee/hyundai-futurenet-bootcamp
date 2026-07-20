package kr.or.kosa.filter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.constant.SecurityConstants;
import kr.or.kosa.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;

// 모든 요청마다 실행: JWT 가 있으면 검증해서 인증 처리
// 토큰은 (1) Authorization: Bearer <jwt> 헤더  또는 (2) token 쿠키 에서 읽는다.
//  - (1) Postman / 앱에서 헤더로 보낼 때
//  - (2) 브라우저(프론트)가 로그인 후 자동 전송하는 HttpOnly 쿠키
// (빈 등록은 SecurityConfig 의 @Bean 에서 하고, 서블릿 자동등록은 FilterRegistrationBean 으로 끔)
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService; // CustomerUserDetailService 주입

	@Autowired
	private JwtProvider jwtProvider; // 토큰 생성/검증

	// 토큰이 필요 없는(회원가입/로그인/로그아웃) 경로
	private static final List<String> EXCLUDE_URLS =
			Arrays.asList("/login", "/register", "/auth/login", "/auth/signup", "/auth/logout");

	private boolean shouldExclude(String requestURI) {
		return EXCLUDE_URLS.stream().anyMatch(requestURI::equals);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		if (shouldExclude(requestURI)) {
			filterChain.doFilter(request, response); // 인증 불필요 경로는 통과
			return;
		}

		// 1. 헤더 또는 쿠키에서 토큰 추출
		String jwt = resolveToken(request);

		if (StringUtils.hasText(jwt)) {
			try {
				// 2. 토큰 검증 후 사용자ID 추출
				String username = jwtProvider.getUsername(jwt);
				log.info("JWT 인증 시도 username: {}", username);

				// 3. 실제 사용자 조회 (권한 로딩)
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				if (userDetails != null) {
					UsernamePasswordAuthenticationToken authentication =
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (Exception e) {
				// 토큰이 잘못됐거나 만료된 경우: 인증 없이 통과 (뒤에서 401/403 처리)
				log.error("JWT 검증 실패: {}", e.getMessage());
			}
		}

		filterChain.doFilter(request, response);
	}

	// Authorization 헤더(Bearer) 우선, 없으면 token 쿠키에서 추출
	private String resolveToken(HttpServletRequest request) {
		String header = request.getHeader(SecurityConstants.TOKEN_HEADER);
		if (StringUtils.hasText(header) && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return header.substring(SecurityConstants.TOKEN_PREFIX.length());
		}

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (SecurityConstants.TOKEN_COOKIE.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
