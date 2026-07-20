package kr.or.kosa.dto;

// 에러 응답: { "status": 401, "error": "Unauthorized", "message": "..." }
// Postman 에서 실패 원인을 JSON 으로 바로 확인할 수 있게 함
public record ApiError(int status, String error, String message) {
}
