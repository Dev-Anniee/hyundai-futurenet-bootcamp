package kr.or.kosa.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.or.kosa.filter.JwtAuthenticationFilter;
import kr.or.kosa.service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(csrf -> csrf.disable())
			.formLogin(login -> login.disable())
			.httpBasic(basic -> basic.disable())
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/auth/signup", "/auth/login", "/auth/logout").permitAll()
					.requestMatchers("/auth/me").authenticated()   // JWT 필요
					.requestMatchers("/h2-console/**").permitAll()
					// 게시판: 조회(GET)는 누구나, 등록/수정/삭제는 로그인 필요
					.requestMatchers(HttpMethod.GET, "/boards", "/boards/**").permitAll()
					.requestMatchers("/boards", "/boards/**").authenticated()
					.requestMatchers("/user/**").hasRole("USER")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().permitAll())
			// 인증 실패(토큰 없음/만료) → 401, 권한 부족 → 403 을 JSON 으로 응답
			.exceptionHandling(ex -> ex
					.authenticationEntryPoint((request, response, e) ->
							writeJsonError(response, HttpStatus.UNAUTHORIZED, "인증이 필요합니다. (로그인 또는 토큰 확인)"))
					.accessDeniedHandler((request, response, e) ->
							writeJsonError(response, HttpStatus.FORBIDDEN, "접근 권한이 없습니다.")))
			// H2 콘솔이 iframe 으로 뜨도록 허용
			.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
			.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// 실패 응답을 JSON 으로 직접 써주는 헬퍼
	private void writeJsonError(jakarta.servlet.http.HttpServletResponse response, HttpStatus status, String message)
			throws java.io.IOException {
		response.setStatus(status.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(
				String.format("{\"status\":%d,\"error\":\"%s\",\"message\":\"%s\"}",
						status.value(), status.getReasonPhrase(), message));
	}

	// CORS 설정 (프론트가 localhost 든 LAN IP(192.168.x.x 등) 든 접속 가능하게)
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		// setAllowedOrigins(정확히 일치)가 아니라 setAllowedOriginPatterns(패턴 허용) 사용
		// → localhost 뿐 아니라 192.168.x.x:포트 같은 내부망 IP 로 접속해도 CORS 통과
		config.setAllowedOriginPatterns(Arrays.asList(
				"http://localhost:*",
				"http://127.0.0.1:*",
				"http://192.168.*.*:*",
				"http://10.*.*.*:*",
				"http://172.16.*.*:*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setExposedHeaders(Arrays.asList("Authorization"));
		config.setAllowCredentials(true); // 쿠키/인증정보 허용

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// mybatis 연동 loadUserByUsername 구현체
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomerUserDetailService();
	}

	// JWT 인증 필터 (@Autowired 필드는 이 @Bean 인스턴스에도 주입됨)
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	// 위 필터가 서블릿 컨테이너에 '자동 등록'되어 두 번 실행되는 것을 방지
	// (Security 필터 체인에서만 동작하도록)
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(JwtAuthenticationFilter filter) {
		FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
		registration.setEnabled(false);
		return registration;
	}
}
