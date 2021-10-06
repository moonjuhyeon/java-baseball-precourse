package baseball.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PlayerInputVest 테스트")
public class PlayerInputViewTest {

	@DisplayName("InputNumber 테스트")
	@Test
	void inputNumberTest() {
		// given
		String input = "123";
		// when
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		// then
		assertThat(PlayerInputView.inputNumbers()).isEqualTo(input);
	}

	@DisplayName("InputGameStatus 테스트")
	@Test
	void inputGameStatusTest() {
		// given
		String input = "1";
		// when
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		// then
		assertThat(PlayerInputView.inputGameStatus()).isEqualTo(input);
	}
}