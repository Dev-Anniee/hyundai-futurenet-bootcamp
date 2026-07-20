package kr.or.kosa.exception;

// 이미 존재하는 아이디로 회원가입 시도할 때 발생 → HTTP 409
public class DuplicateUsernameException extends RuntimeException {
	public DuplicateUsernameException(String message) {
		super(message);
	}
}
