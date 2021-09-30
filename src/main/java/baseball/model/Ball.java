package baseball.model;

import baseball.code.BallCount;

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
      throw new IllegalArgumentException("1-9까지의 숫자를 입력하세요.");
    }
  }

  public void isBetweenIndexRange() {
    if(this.index>=0 && this.index<3){
      throw new IllegalArgumentException("최대 세 자리 수까지 입력가능합니다.");
    }
  }

  public BallCount compareBall(Ball ball) {
    if(this.index==ball.index && this.number==ball.index){
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
      return new Ball(this);
    }
  }
}

