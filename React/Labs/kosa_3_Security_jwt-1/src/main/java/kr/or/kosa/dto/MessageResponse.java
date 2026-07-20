package kr.or.kosa.dto;

// 단순 메시지 응답: { "message": "..." } - 로그아웃 등에 사용
public record MessageResponse(String message) {
}
