package baseball.code;

public enum GameStatus {
	START_GAME("1"),
	END_GAME("2"),
	END_RESULT("3스트라이크");

	private String gameStatus;

	GameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getGameStatus() {
		return this.gameStatus;
	}
}
