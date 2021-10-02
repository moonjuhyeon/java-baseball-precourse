package baseball.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BallCount {
	STRIKE("스트라이크"),
	BALL("볼"),
	NOTHING("낫싱");

	private String ballCount;

	BallCount(String ballCount) {
		this.ballCount = ballCount;
	}

	public static String createBallCountString(int strike, int ball) {
		if (strike == 0 && ball == 0) {
			return NOTHING.ballCount;
		}
		if (strike == 0) {
			return ball + BALL.ballCount;
		}
		if (ball == 0) {
			return strike + STRIKE.ballCount;
		}
		return strike + STRIKE.ballCount + " " + ball + BALL.ballCount;
	}

	public static Map<String, Integer> convertBallCountListToMap(List<BallCount> ballCountList) {
		Map<String, Integer> ballCountMap = new HashMap<>();
		for (BallCount ballCount : ballCountList) {
			ballCountMap.put(ballCount.getBallCount(), ballCountMap.getOrDefault(ballCount.getBallCount(), 0) + 1);
		}
		return ballCountMap;
	}

	public static String convertBallCountMapToString(Map<String, Integer> ballCountMap) {
		return BallCount.createBallCountString(ballCountMap.getOrDefault(BallCount.STRIKE.getBallCount(), 0),
			ballCountMap.getOrDefault(BallCount.BALL.getBallCount(), 0));
	}

	public static String convertBallCountListToString(List<BallCount> ballCountList) {
		return convertBallCountMapToString(convertBallCountListToMap(ballCountList));
	}

	public String getBallCount() {
		return this.ballCount;
	}
}