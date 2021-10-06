package baseball.code;

public enum BallCount {
	STRIKE("스트라이크"),
	BALL("볼"),
	NOTHING("낫싱");

	private String ballCount;

	BallCount(String ballCount) {
		this.ballCount = ballCount;
	}

	public String getBallCount() {
		return this.ballCount;
	}
}