package baseball.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import baseball.code.BallCount;
import baseball.code.ErrorCode;
import baseball.model.Balls.BallsBuilder;

@DisplayName("Balls 테스트")
class BallsTest {

	@DisplayName("Balls Java Bean 생성테스트")
	@Test
	void createBallsJavaBean() {
		// given // when
		Balls balls = new Balls();
		// then
		assertThat(balls).isInstanceOf(Balls.class);
	}

	@DisplayName("Balls Builder 생성 테스트")
	@Test
	void createBallsBuilder() {
		// given // when
		Balls balls = new Balls.BallsBuilder().ballArray("123").build();
		// then
		assertThat(balls).isInstanceOf(Balls.class);
		assertThat(balls.getBallList()).isInstanceOf(List.class);
	}

	@DisplayName("Balls RandomBuilder 생성 테스트, Randoms를 사용한 무작위 겹치지 않는 세 가지 수 객체 생성")
	@Test
	void createRandomNumbersUsingBallRandomBuilderTest() {
		// given
		Balls balls = new Balls.BallsRandomsBuilder().build();
		// when
		List<Ball> ballList = balls.getBallList();
		// then
		assertThat(ballList).isInstanceOf(List.class);
		assertThat(ballList).isNotNull();
		assertThat(ballList).hasSize(3);
		assertThat(ballList.get(0)).isInstanceOf(Ball.class);
		assertThat(ballList.get(0).compareBall(ballList.get(0))).isEqualTo(BallCount.STRIKE);
		assertThat(ballList.get(0).getNumber()).isNotEqualTo(ballList.get(1).getNumber());
		assertThat(ballList.get(0).getNumber()).isNotEqualTo(ballList.get(2).getNumber());
		assertThat(ballList.get(1).getNumber()).isNotEqualTo(ballList.get(2).getNumber());
	}

	@DisplayName("Balls Builder 플레이어 입력 값이 세 자리가 아니거나 중복 값이 존재하는 예외상황 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"1234", "4444", "4", "74", "2", "22"})
	void createPlayerBallsOver3NumbersException(String input) {
		assertThatThrownBy(() -> {
			new BallsBuilder().ballArray(input).build();
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining(ErrorCode.INVALID_BALL_INDEX_RANGE.getMessage());
	}

	@DisplayName("Balls Builder 플레이어 입력 값이 빈 값인 예외상황 테스트")
	@Test
	void createPlayerBallsInputNull() {
		assertThatThrownBy(() -> {
			new BallsBuilder().ballArray("").build();
		}).isInstanceOf(NullPointerException.class);
	}

	@DisplayName("두 Balls를 비교하여 BallCountList를 만드는 함수 테스트")
	@Test
	void createBallCountListCompare() {
		// given
		Balls computerBalls = new Balls.BallsBuilder().ballArray("123").build();
		Balls playerBalls = new Balls.BallsBuilder().ballArray("123").build();
		// when
		List<BallCount> ballCountList = computerBalls.compareBalls(playerBalls);
		// then
		assertThat(ballCountList).isInstanceOf(List.class);
		assertThat(ballCountList.size()).isEqualTo(9);
	}

	@DisplayName("두 Balls를 비교한 BallCountList를 결과 String으로 출력하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {
		"124, 123, 2스트라이크",
		"124, 567, 낫싱",
		"132, 123, 1스트라이크 2볼",
		"123, 145, 1스트라이크",
		"123, 123, 3스트라이크",
		"312, 123, 3볼"
	})
	void createResultStringUsingBallsCompare(String computer, String player, String wantResult) {
		// given
		Balls computerBalls = new Balls.BallsBuilder().ballArray(computer).build();
		Balls playerBalls = new Balls.BallsBuilder().ballArray(player).build();
		List<BallCount> ballCountList = computerBalls.compareBalls(playerBalls);
		// when
		String result = BallCount.convertBallCountListToString(ballCountList);
		// then
		assertThat(result).isEqualTo(wantResult);
	}

}