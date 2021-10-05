package baseball.controller;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import baseball.code.GameStatus;
import baseball.model.Balls;

@DisplayName("BaseballController 테스트")
public class BaseballControllerTest {

	private ByteArrayOutputStream byteArrayOutputStream;

	@DisplayName("playBaseball 테스트")
	@ParameterizedTest
	@CsvSource(value = {"713, 713", "123, 123"})
	void test(String computer, String player) {
		// given
		byteArrayOutputStream = new ByteArrayOutputStream();
		Balls computerBalls = new Balls.BallsBuilder().ballArray(computer).build();
		// when
		InputStream inputStream = new ByteArrayInputStream(player.getBytes());
		System.setIn(inputStream);
		System.setOut(new PrintStream(byteArrayOutputStream));
		BaseballController.resultOfBaseball(computerBalls);
		// then
		assertThat(byteArrayOutputStream.toString().trim()).contains(GameStatus.END_RESULT.getGameStatus());
	}
}