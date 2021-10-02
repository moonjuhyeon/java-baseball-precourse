package baseball.code;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BallCountTest {

	private List<BallCount> ballCountList;

	@BeforeEach
	void setup() {
		ballCountList = new ArrayList<>();
	}

	@DisplayName("BallCountList를 Ballcount를 카운팅하는 Map으로 변환하는 함수 테스트")
	@Test
	void convertBallCountListToMap() {
		// given
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.BALL);
		// when
		Map<String, Integer> ballCountMap = BallCount.convertBallCountListToMap(ballCountList);
		// then
		assertThat(ballCountMap.get(BallCount.STRIKE.getBallCount())).isEqualTo(2);
		assertThat(ballCountMap.get(BallCount.BALL.getBallCount())).isEqualTo(1);
	}

	@DisplayName("2STRIKE, 1BALL BallCountMap을 결과 String으로 변환하는 함수 테스트")
	@Test
	void convertBallCountMapToStringStrikeNBall() {
		// given
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.BALL);
		// when
		String result = BallCount.convertBallCountListToString(ballCountList);
		// then
		assertThat(result).isEqualTo("2스트라이크 1볼");
	}

	@DisplayName("2STRIKE, 1NOTHING BallCountMap 결과 String으로 변환하는 함수 테스트")
	@Test
	void convertBallCountMapToStringOnlyStrike() {
		// given
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.STRIKE);
		ballCountList.add(BallCount.NOTHING);
		// when
		String result = BallCount.convertBallCountListToString(ballCountList);
		// then
		assertThat(result).isEqualTo("2스트라이크");
	}

	@DisplayName("2BALL, NOTHING BallCountMap을 결과 String으로 변환하는 함수 테스트")
	@Test
	void convertBallCountMapToStringOnlyBall() {
		// given
		ballCountList.add(BallCount.BALL);
		ballCountList.add(BallCount.BALL);
		ballCountList.add(BallCount.NOTHING);
		// when
		String result = BallCount.convertBallCountListToString(ballCountList);
		// then
		assertThat(result).isEqualTo("2볼");
	}

	@DisplayName("3NOTHING BallCountMap을 결과 String으로 변환하는 함수 테스트")
	@Test
	void convertBallCountMapToStringNothing() {
		// given
		ballCountList.add(BallCount.NOTHING);
		ballCountList.add(BallCount.NOTHING);
		ballCountList.add(BallCount.NOTHING);
		// when
		String result = BallCount.convertBallCountListToString(ballCountList);
		// then
		assertThat(result).isEqualTo("낫싱");
	}

}