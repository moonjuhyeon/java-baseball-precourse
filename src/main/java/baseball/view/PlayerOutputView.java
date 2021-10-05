package baseball.view;

import baseball.exception.BallException;

public class PlayerOutputView {
	public static void printResultOutput(String result) {
		System.out.println(result);
	}

	public static void printThisGameEnd() {
		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
	}

	public static void printError(BallException ballException) {
		System.out.println(ballException.getMessage());
	}
}