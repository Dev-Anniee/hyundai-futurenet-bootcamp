package kr.or.kosa.dto;

import kr.or.kosa.domain.User;

// 사용자 정보 응답 (비밀번호 제외) - /auth/me, /auth/signup 응답에 사용
public record UserResponse(Long id, String username, String name, String role) {

	public static UserResponse from(User user) {
		return new UserResponse(user.getId(), user.getUsername(), user.getName(), user.getRole());
	}
}
