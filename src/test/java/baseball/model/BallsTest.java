package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.code.BallCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Balls 테스트")
class BallsTest {

  @DisplayName("Balls Java Bean 생성테스트")
  @Test
  void createBallsJavaBean(){
    // given // when
    Balls balls = new Balls();

    // then
    assertThat(balls).isInstanceOf(Balls.class);
  }

  @DisplayName("Balls Builder 생성 테스트")
  @Test
  void createBallsBuilder(){
    // given // when
    Balls balls = new Balls.BallsBuilder()
        .ballArray(new Ball[]{})
        .build();

    // then
    assertThat(balls).isInstanceOf(Balls.class);
    assertThat(balls.getBallArray()).isInstanceOf(Ball[].class);
  }

  @DisplayName("Balls RandomBuilder 생성 테스트, Randoms를 사용한 무작위 겹치지 않는 세 가지 수 객체 생성")
  @Test
  void createNumbersUsingRandomSetTest(){
    // given
    Balls balls = new Balls.BallsRandomsBuilder().build();

    // when
    Ball[] ballArray = balls.getBallArray();

    // then
    assertThat(ballArray).isInstanceOf(Ball[].class);
    assertThat(ballArray).isNotNull();
    assertThat(ballArray).hasSize(3);
    assertThat(ballArray[0]).isInstanceOf(Ball.class);
    assertThat(ballArray[0].compareBall(ballArray[0])).isEqualTo(BallCount.STRIKE);
    assertThat(ballArray[0].getNumber()).isNotEqualTo(ballArray[1].getNumber());
    assertThat(ballArray[0].getNumber()).isNotEqualTo(ballArray[2].getNumber());
    assertThat(ballArray[1].getNumber()).isNotEqualTo(ballArray[2].getNumber());
  }

}