package kr.or.kosa.dto;

// 회원가입 요청 바디: { "username": "...", "password": "...", "name": "..." }
public record SignupRequest(String username, String password, String name) {
}
