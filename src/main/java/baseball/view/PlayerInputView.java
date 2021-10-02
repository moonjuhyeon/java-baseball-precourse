package baseball.view;

import nextstep.utils.Console;

public class PlayerInputView {

	public static String inputNumbers() {
		System.out.println("숫자를 입력해주세요 : ");
		return Console.readLine();
	}

	public static String inputGameStatus() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		return Console.readLine();
	}
}
