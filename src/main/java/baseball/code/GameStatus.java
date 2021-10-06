package baseball.code;

import baseball.exception.BallException;

public enum GameStatus {
	START_GAME("1"),
	END_GAME("2"),
	END_RESULT("3스트라이크");

	private String gameStatus;

	GameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public static String validInputGameStatus(String input) {
		if (!(input.equals(START_GAME.gameStatus) || input.equals(END_GAME.gameStatus))) {
			throw new BallException(ErrorCode.INVALID_INPUT_VALUE.getMessage());
		}
		return input;
	}

	public String getGameStatus() {
		return this.gameStatus;
	}
}
