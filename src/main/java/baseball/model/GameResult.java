package baseball.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import baseball.code.BallCount;

public class GameResult {
	private int strike;
	private int ball;

	public GameResult(GameResultBuilder gameResultBuilder) {
		this.strike = gameResultBuilder.strike;
		this.ball = gameResultBuilder.ball;
	}

	public boolean isLoose() {
		return this.strike != 3;
	}

	public String getGameResultString() {
		if (strike == 0 && ball == 0) {
			return BallCount.NOTHING.getBallCount();
		}
		if (strike == 0) {
			return ball + BallCount.BALL.getBallCount();
		}
		if (ball == 0) {
			return strike + BallCount.STRIKE.getBallCount();
		}
		return strike + BallCount.STRIKE.getBallCount() + " " + ball + BallCount.BALL.getBallCount();
	}

	public int getStrike() {
		return strike;
	}

	public int getBall() {
		return ball;
	}

	public static class GameResultBuilder {
		private Balls playerBalls;
		private Balls computerBalls;
		private int strike;
		private int ball;

		public Map<BallCount, Integer> convertBallCountListToMap(List<BallCount> ballCountList) {
			Map<BallCount, Integer> ballCountMap = new HashMap<>();
			for (BallCount ballCount : ballCountList) {
				ballCountMap.put(ballCount,
					ballCountMap.getOrDefault(ballCount, 0) + 1);
			}
			return ballCountMap;
		}

		public void convertBallCountMapToField(Map<BallCount, Integer> ballCountMap) {
			this.strike = ballCountMap.getOrDefault(BallCount.STRIKE, 0);
			this.ball = ballCountMap.getOrDefault(BallCount.BALL, 0);
		}

		public GameResultBuilder compareBalls(Balls computerBalls, Balls playerBalls) {
			this.computerBalls = computerBalls;
			this.playerBalls = playerBalls;
			return this;
		}

		public GameResult build() {
			List<BallCount> ballCountList = computerBalls.compareBalls(playerBalls);
			this.convertBallCountMapToField(this.convertBallCountListToMap(ballCountList));
			return new GameResult(this);
		}
	}
}
