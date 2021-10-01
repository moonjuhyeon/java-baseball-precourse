package baseball.model;

import baseball.model.Ball.BallBuilder;
import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Randoms;

public class Balls {
  private final static int MIN_NUMBER_RANGE = 1;
  private final static int MAX_NUMBER_RANGE = 9;
  private final static int MAX_INDEX_RANGE = 2;

  private Ball[] ballArray;

  public Ball[] getBallArray() {
    return ballArray;
  }

  public Balls(){

  }

  public Balls(BallsBuilder ballsBuilder){
    this.ballArray = ballsBuilder.ballArray;
  }

  public Balls(BallsRandomsBuilder ballsRandomsBuilder) {
    this.ballArray = ballsRandomsBuilder.ballArray;
  }

  public static class BallsBuilder {
    private Ball[] ballArray;

    public BallsBuilder ballArray(Ball[] ballArray){
      this.ballArray = ballArray;
      return this;
    }


    public Balls build(){
      return new Balls(this);
    }
  }

  public static class BallsRandomsBuilder {
    private Ball[] ballArray;

    private Set<Integer> createNumberSetUsingRandoms(){
      Set<Integer> randomsNumberSet = new HashSet<>();

      while(randomsNumberSet.size()<=MAX_INDEX_RANGE){
        randomsNumberSet.add(Randoms.pickNumberInRange(MIN_NUMBER_RANGE, MAX_NUMBER_RANGE));
      }
      return randomsNumberSet;
    }

    private void createBallArrayUsingRandoms(){
      ballArray = new Ball[MAX_INDEX_RANGE+1];
      int i = 0;
      for(int number : createNumberSetUsingRandoms()){
        ballArray[i] = new BallBuilder().index(i).number(number).build();
        i++;
      }
    }

    public Balls build(){
      createBallArrayUsingRandoms();
      return new Balls(this);
    }

  }
}
