package baseball.code;

public enum ErrorCode {
	INVALID_BALL_NUMBER_RANGE("BALL001", "[ERROR] 1-9까지의 숫자를 입력하세요."),
	INVALID_BALL_INDEX_RANGE("BALL002", "[ERROR] 서로 다른 세자리 수를 입력 하세요."),
	INVALID_INPUT_VALUE("INPUT003", "[ERROR] 잘못된 입력입니다.");

	private String errorCode;
	private String message;

	ErrorCode(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getMessage() {
		return this.message;
	}
}
