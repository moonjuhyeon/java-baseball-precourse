package baseball.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PlayerOutputView 테스트")
public class PlayerOutputViewTest {

	private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(byteArrayOutputStream));
	}

	@DisplayName("printResultOutput 테스트")
	@Test
	void printResultOutputTest() {
		// given
		String result = "3스트라이크";
		// when
		PlayerOutputView.printResultOutput(result);
		// then
		assertThat(byteArrayOutputStream.toString().trim()).isEqualTo(result.trim());
	}

	@DisplayName("printGameEndOutput 테스트")
	@Test
	void printGameEndOutputTest() {
		// given
		String gameEnd = "3개의 숫자를 모두 맞히셨습니다! 게임 끝";
		// when
		PlayerOutputView.printThisGameEnd();
		// then
		assertThat(byteArrayOutputStream.toString().trim()).isEqualTo(gameEnd.trim());
	}
}