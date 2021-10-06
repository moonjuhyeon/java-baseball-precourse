package baseball.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;

import baseball.code.BallCount;
import baseball.code.GameStatus;

@DisplayName("GameResult 테스트")
class GameResultTest {

	@InjectMocks
	private final GameResult.GameResultBuilder gameResultBuilder = new GameResult.GameResultBuilder();

	@Test
	void createGameResult() {
		// given
		Balls computerBalls = new Balls.BallsBuilder().ballArray("123").build();
		Balls playerBalls = new Balls.BallsBuilder().ballArray("123").build();
		// when
		GameResult gameResult = new GameResult.GameResultBuilder()
			.compareBalls(computerBalls, playerBalls)
			.build();
		// then
		assertAll(
			() -> assertThat(gameResult).isInstanceOf(GameResult.class),
			() -> assertThat(gameResult.getStrike()).isEqualTo(3),
			() -> assertThat(gameResult.getBall()).isZero(),
			() -> assertThat(gameResult.getGameResultString()).isEqualTo(GameStatus.END_RESULT.getGameStatus())
		);
	}

	@DisplayName("BallCounCodetList를 BallCount를 카운팅하는 Map으로 변환하는 함수 테스트")
	@Test
	void convertBallCountListToMap() {
		// given
		List<BallCount> ballCountList = new ArrayList<>();
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.BALL);
		// when
		Map<BallCount, Integer> ballCountMap = gameResultBuilder.convertBallCountListToMap(ballCountList);
		// then
		assertAll(
			() -> assertThat(ballCountMap).containsEntry(BallCount.STRIKE, 2),
			() -> assertThat(ballCountMap).containsEntry(BallCount.BALL, 1)
		);
	}

	@DisplayName("두 Balls를 비교한 BallCountList를 결과 String으로 출력하는 테스트")
	@ParameterizedTest(name = "{index} ==> computer {0}, player {1}, wantResult {2}")
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
		// when
		GameResult gameResult = new GameResult.GameResultBuilder().compareBalls(computerBalls, playerBalls).build();
		// then
		assertThat(gameResult.getGameResultString()).isEqualTo(wantResult);
	}
}