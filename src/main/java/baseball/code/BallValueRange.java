package baseball.code;

public enum BallValueRange {
	MIN_NUMBER_RANGE(1),
	MAX_NUMBER_RANGE(9),
	MIN_INDEX_RANGE(0),
	MAX_INDEX_RANGE(2);

	private int value;

	BallValueRange(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
