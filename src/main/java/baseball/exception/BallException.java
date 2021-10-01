package baseball.exception;

public class BallException extends RuntimeException{
  public BallException(){
    super();
  }

  public BallException(String message){
    super(message);
  }
}
