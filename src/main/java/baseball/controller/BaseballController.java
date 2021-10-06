package baseball.controller;

import baseball.code.GameStatus;
import baseball.exception.BallException;
import baseball.model.Balls;
import baseball.model.GameResult;
import baseball.view.PlayerInputView;
import baseball.view.PlayerOutputView;

public class BaseballController {
	private static GameResult gameResult;

	public static void playBaseball() {
		do {
			Balls computerBalls = new Balls.BallsRandomsBuilder().build();
			resultOfBaseball(computerBalls);
			PlayerOutputView.printThisGameEnd();
		} while (GameStatus.isRestart(validGameStatusInput()));
	}

	public static String validGameStatusInput() {
		try {
			return GameStatus.validInputGameStatus(PlayerInputView.inputGameStatus());
		} catch (BallException ballException) {
			PlayerOutputView.printError(ballException);
			return validGameStatusInput();
		}
	}

	public static void resultOfBaseball(Balls computerBalls) {
		do {
			Balls playerBalls = createPlayerBalls();
			gameResult = new GameResult
				.GameResultBuilder().compareBalls(computerBalls, playerBalls).build();
			PlayerOutputView.printResultOutput(gameResult.getGameResultString());
		} while (gameResult.isLoose());
	}

	public static Balls createPlayerBalls() {
		try {
			String input = PlayerInputView.inputNumbers();
			return new Balls.BallsBuilder().ballArray(input).build();
		} catch (BallException exception) {
			PlayerOutputView.printError(exception);
			return createPlayerBalls();
		}
	}
}

