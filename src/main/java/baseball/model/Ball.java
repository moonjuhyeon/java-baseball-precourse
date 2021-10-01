package baseball.model;
import baseball.code.BallCount;
import baseball.code.ErrorCode;
import baseball.exception.BallException;

public class Ball {
  private int number;
  private int index;

  public int getNumber() {
    return number;
  }

  public int getIndex() {
    return index;
  }

  public void isBetweenNumberRange() {
    if(this.number>9 || this.number<1){
      throw new BallException(ErrorCode.INVALID_BALL_NUMBER_RANGE.getMessage());
    }
  }

  public void isBetweenIndexRange() {
    if(this.index<0 || this.index>2){
      throw new BallException(ErrorCode.INVALID_BALL_INDEX_RANGE.getMessage());
    }
  }

  public BallCount compareBall(Ball ball) {
    if(this.index==ball.index && this.number==ball.number){
      return BallCount.STRIKE;
    }

    if(this.index!=ball.index && this.number==ball.number){
      return BallCount.BALL;
    }

    return BallCount.NOTHING;
  }

  public Ball(){}

  public Ball(BallBuilder ballBuilder){
    this.number = ballBuilder.number;
    this.index = ballBuilder.index;
  }

  public static class BallBuilder{
    private int number;
    private int index;

    public BallBuilder number(int number){
      this.number = number;
      return this;
    }

    public BallBuilder index(int index){
      this.index = index;
      return this;
    }

    public Ball build(){
      Ball ball = new Ball(this);
      ball.isBetweenIndexRange();
      ball.isBetweenNumberRange();
      return ball;
    }
  }
}