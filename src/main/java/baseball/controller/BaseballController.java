package baseball.controller;

import baseball.code.BallCount;
import baseball.code.GameStatus;
import baseball.exception.BallException;
import baseball.model.Balls;
import baseball.view.PlayerInputView;
import baseball.view.PlayerOutputView;

public class BaseballController {
	public static String GAME_STATUS = GameStatus.START_GAME.getGameStatus();

	public static void playBaseball() {
		do {
			Balls computerBalls = new Balls.BallsRandomsBuilder().build();
			resultOfBaseball(computerBalls);
			PlayerOutputView.printThisGameEnd();
			GAME_STATUS = PlayerInputView.inputGameStatus();
		} while (GAME_STATUS.equals(GameStatus.START_GAME.getGameStatus()));
	}

	public static void resultOfBaseball(Balls computerBalls) {
		String result;
		do {
			Balls playerBalls = createPlayerBalls();
			result = BallCount.convertBallCountListToString(computerBalls.compareBalls(playerBalls));
			PlayerOutputView.printResultOutput(result);
		} while (!GameStatus.END_RESULT.getGameStatus().equals(result));
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

