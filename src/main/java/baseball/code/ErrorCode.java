package baseball.code;

public enum ErrorCode {
  INVALID_BALL_NUMBER_RANGE("BALL001", "1-9까지의 숫자를 입력하세요."),
  INVALID_BALL_INDEX_RANGE("BALL002", "최대 세 자리 수까지 입력가능합니다."),
  INVALID_DUPLICATE_INPUT_VALUE("INPUT001", "서로 다른 수만 입력 가능합니다."),
  INVALID_INPUT_VALUE("INPUT001", "잘못된 입력입니다.");

  private String errorCode;
  private String message;

  ErrorCode(String errorCode, String message){
    this.errorCode = errorCode;
    this.message = message;
  }

  public String getErrorCode(){
    return this.errorCode;
  }

  public String getMessage(){
    return this.message;
  }
}
