package kr.or.kosa.constroller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.constant.SecurityConstants;
import kr.or.kosa.domain.AuthenticationRequest;
import kr.or.kosa.dto.LoginResponse;
import kr.or.kosa.dto.MessageResponse;
import kr.or.kosa.dto.SignupRequest;
import kr.or.kosa.dto.UserResponse;
import kr.or.kosa.jwt.JwtProvider;
import kr.or.kosa.service.AuthService;
import lombok.extern.slf4j.Slf4j;

// 프론트(ex-18-task-jwt)가 사용하는 인증 API
//   POST /auth/signup   회원가입
//   POST /auth/login    로그인 (JWT 발급 + 쿠키)
//   GET  /auth/me       내 정보 (JWT 필요)
//   POST /auth/logout   로그아웃 (쿠키 제거)
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// 1. 회원가입
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> signup(@RequestBody SignupRequest request) {
		UserResponse user = authService.signup(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(user); // 201
	}

	// 2. 로그인 → JWT 를 응답 바디(token) + HttpOnly 쿠키로 전달
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody AuthenticationRequest request) {
		LoginResponse result = authService.login(request.getUsername(), request.getPassword());

		// JWT 를 쿠키로도 내려서 브라우저(프론트)가 자동으로 인증되게 함
		ResponseCookie cookie = buildTokenCookie(result.token(), JwtProvider.TOKEN_VALIDITY_MILLIS / 1000);

		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(result);
	}

	// 3. 내 정보 (SecurityConfig 에서 인증 필수로 설정 → 여기 오면 이미 인증된 사용자)
	@GetMapping("/me")
	public ResponseEntity<UserResponse> me(Authentication authentication) {
		String username = authentication.getName();
		return ResponseEntity.ok(authService.getByUsername(username));
	}

	// 4. 로그아웃 → 쿠키 만료(maxAge=0)
	@PostMapping("/logout")
	public ResponseEntity<MessageResponse> logout() {
		ResponseCookie expired = buildTokenCookie("", 0);
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, expired.toString())
				.body(new MessageResponse("로그아웃 되었습니다."));
	}

	// JWT 쿠키 생성 헬퍼
	private ResponseCookie buildTokenCookie(String token, long maxAgeSeconds) {
		return ResponseCookie.from(SecurityConstants.TOKEN_COOKIE, token)
				.httpOnly(true)   // JS 에서 접근 불가 (XSS 방어)
				.secure(false)    // 로컬 http 개발용 (운영 https 라면 true)
				.path("/")
				.maxAge(maxAgeSeconds)
				.sameSite("Lax")
				.build();
	}
}
