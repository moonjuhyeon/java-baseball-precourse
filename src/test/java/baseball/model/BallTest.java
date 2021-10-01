package baseball.model;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.code.BallCount;
import baseball.model.Ball.BallBuilder;
import nextstep.utils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Ball 테스트")
class BallTest {

  @DisplayName("Ball Java Bean 생성 테스트")
  @Test
  void createdBallJavaBean(){
    // given // when
    Ball ball = new Ball();

    // then
    assertThat(ball).isInstanceOf(Ball.class);
    assertThat(ball.getNumber()).isInstanceOf(Integer.class);
  }

  @DisplayName("Ball Builder 생성 테스트")
  @Test
  void creatBallBuilder(){
    // given // when
    Ball ball = new Ball.BallBuilder()
        .number(1)
        .index(1)
        .build();

    // then
    assertThat(ball).isInstanceOf(Ball.class);
    assertThat(ball.getNumber()).isInstanceOf(Integer.class);
    assertThat(ball.getNumber()).isEqualTo(1);
    assertThat(ball.getIndex()).isInstanceOf(Integer.class);
    assertThat(ball.getIndex()).isEqualTo(1);
  }

  @DisplayName("BallNumber 1-9 숫자 범위 유효성 예외처리 테스트")
  @ParameterizedTest
  @ValueSource(ints = { 0, 10, -1, 100, Integer.MAX_VALUE, Integer.MIN_VALUE })
  void validationBetweenBallNumberRangeThrowException(int number) {
    // given // when
    Ball ball = new BallBuilder()
        .number(number)
        .build();

    // then
    assertThatThrownBy(ball::isBetweenNumberRange)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("1-9까지의 숫자를 입력하세요.");
  }

  @DisplayName("BallNumber 1-9 숫자 범위 유효성 테스트")
  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7 ,8, 9 })
  void validationBetweenBallNumberRange(int number) {
    // given // when
    Ball ball = new BallBuilder()
        .number(number)
        .build();

    // then
    assertThat(ball.getNumber()).isBetween(1, 9);
  }

  @DisplayName("BallIndex 0-2 인덱스 범위 유효성 예외처리 테스트")
  @ParameterizedTest
  @ValueSource(ints = { 3, -1, 100, Integer.MAX_VALUE, Integer.MIN_VALUE })
  void validationBetweenBallIndexRangeThrowException(int index) {
    // given // when
    Ball ball = new BallBuilder()
        .index(index)
        .build();

    // then
    assertThatThrownBy(ball::isBetweenIndexRange)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("최대 세 자리 수까지 입력가능합니다.");
  }

  @DisplayName("BallIndex 0-2 인덱스 범위 유효성 테스트")
  @ParameterizedTest
  @ValueSource(ints = { 0, 1, 2 })
  void validationBetweenBallIndexRange(int index) {
    // given // when
    Ball ball = new BallBuilder()
        .index(index)
        .build();

    // then
    assertThat(ball.getNumber()).isBetween(0, 2);
  }

  @DisplayName("같은 자리, 같은 숫자일 때 BallCount STRIKE 반환 테스트")
  @Test
  void compareBallReturnStrike(){
    // given
    Ball ballA = new BallBuilder()
        .number(0)
        .index(0)
        .build();
    Ball ballB = new BallBuilder()
        .number(0)
        .index(0)
        .build();
    // when
    BallCount ballCount = ballA.compareBall(ballB);

    // then
    assertThat(ballCount).isEqualTo(BallCount.STRIKE);
  }

  @DisplayName("다른 자리, 같은 숫자일 때 BallCount Ball 반환 테스트")
  @Test
  void compareBallReturnBall(){
    // given
    Ball ballA = new BallBuilder()
        .number(0)
        .index(0)
        .build();
    Ball ballB = new BallBuilder()
        .number(0)
        .index(1)
        .build();
    // when
    BallCount ballCount = ballA.compareBall(ballB);

    // then
    assertThat(ballCount).isEqualTo(BallCount.BALL);
  }

  @DisplayName("Ball Randoms 생성 테스트")
  @Test
  void createBallUsingRandoms(){
    // given
    int randomNumber = Randoms.pickNumberInRange(1,9);

    // when
    Ball ball = new BallBuilder()
        .number(randomNumber)
        .build();
    ball.isBetweenNumberRange();

    // then
    assertThat(ball.getNumber()).isEqualTo(randomNumber);
  }
}