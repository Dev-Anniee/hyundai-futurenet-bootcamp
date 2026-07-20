package kr.or.kosa.dto;

import kr.or.kosa.domain.User;

// 로그인 응답: 사용자 정보 + JWT 토큰
// - 프론트(ex-18-task-jwt)는 name 을 화면에 표시
// - Postman 은 token 값을 눈으로 확인 (JWT 는 쿠키로도 함께 내려감)
public record LoginResponse(Long id, String username, String name, String role, String token) {

	public static LoginResponse of(User user, String token) {
		return new LoginResponse(user.getId(), user.getUsername(), user.getName(), user.getRole(), token);
	}
}
