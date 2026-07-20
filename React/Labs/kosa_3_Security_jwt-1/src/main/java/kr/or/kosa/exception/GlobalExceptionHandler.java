package kr.or.kosa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.or.kosa.dto.ApiError;

// 컨트롤러에서 던진 예외를 JSON 응답으로 변환 (Postman 에서 원인 확인 용이)
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 아이디 중복 → 409 Conflict (프론트 Signup 이 409 를 감지)
	@ExceptionHandler(DuplicateUsernameException.class)
	public ResponseEntity<ApiError> handleDuplicate(DuplicateUsernameException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiError(409, "Conflict", e.getMessage()));
	}

	// 아이디/비밀번호 불일치 → 401 Unauthorized (프론트 Login 이 401 을 감지)
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> handleBadCredentials(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ApiError(401, "Unauthorized", e.getMessage()));
	}

	// 본인 글이 아닌데 수정/삭제 시도 → 403 Forbidden
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(new ApiError(403, "Forbidden", e.getMessage()));
	}
}
