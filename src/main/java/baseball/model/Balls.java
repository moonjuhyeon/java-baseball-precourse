package baseball.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseball.code.BallCount;
import baseball.code.BallValueRange;
import baseball.code.ErrorCode;
import baseball.exception.BallException;
import baseball.model.Ball.BallBuilder;
import nextstep.utils.Randoms;

public class Balls {
	private List<BallCount> ballCountList;
	private List<Ball> ballList;

	public Balls() {

	}

	public Balls(BallsBuilder ballsBuilder) {
		this.ballList = ballsBuilder.ballList;
	}

	public Balls(BallsRandomsBuilder ballsRandomsBuilder) {
		this.ballList = ballsRandomsBuilder.ballList;
	}

	public List<Ball> getBallList() {
		return ballList;
	}

	public List<BallCount> compareBalls(Balls playerBalls) {
		ballCountList = new ArrayList<>();
		for (int i = BallValueRange.MIN_INDEX_RANGE.getValue(); i <= BallValueRange.MAX_INDEX_RANGE.getValue(); i++) {
			rotateCompareBalls(i, playerBalls);
		}
		return this.ballCountList;
	}

	private void rotateCompareBalls(int i, Balls playerBalls) {
		for (int j = BallValueRange.MIN_INDEX_RANGE.getValue();
			 j <= BallValueRange.MAX_INDEX_RANGE.getValue(); j++) {
			this.ballCountList.add(this.ballList.get(i).compareBall(playerBalls.getBallList().get(j)));
		}
	}

	public static class BallsBuilder {
		private List<Ball> ballList;

		public BallsBuilder ballArray(String input) {
			this.ballList = createBallList(checkNumericAndBlank(input));
			return this;
		}

		private String checkNumericAndBlank(String input) {
			if (!input.matches("[0-9]+")) {
				throw new BallException(ErrorCode.INVALID_INPUT_VALUE.getMessage());
			}
			if (input.equals("")) {
				throw new BallException(ErrorCode.INVALID_INPUT_VALUE.getMessage());
			}
			return input;
		}

		private List<Ball> createBallList(String input) {
			List<Ball> ballList = new ArrayList<>();
			int i = 0;
			for (char number : checkDupNumbers(input).toCharArray()) {
				ballList.add(createBall(Character.getNumericValue(number), i));
				i++;
			}
			return ballList;
		}

		private String checkDupNumbers(String input) {
			Set<Integer> integerSet = new HashSet<>();
			for (char s : input.toCharArray()) {
				integerSet.add(Character.getNumericValue(s));
			}
			if (integerSet.size() <= BallValueRange.MAX_INDEX_RANGE.getValue()) {
				throw new BallException(ErrorCode.INVALID_BALL_INDEX_RANGE.getMessage());
			}
			return input;
		}

		private Ball createBall(int number, int index) {
			return new BallBuilder().number(number).index(index).build();
		}

		public Balls build() {
			return new Balls(this);
		}
	}

	public static class BallsRandomsBuilder {
		private List<Ball> ballList;

		private List<Integer> createNumberListUsingRandoms() {
			List<Integer> randomsNumberList = new ArrayList<>();
			while (randomsNumberList.size() <= BallValueRange.MAX_INDEX_RANGE.getValue()) {
				checkDuplicateNumber(randomsNumberList, createRandomNumber());
			}
			return randomsNumberList;
		}

		private Integer createRandomNumber() {
			return Randoms.pickNumberInRange(BallValueRange.MIN_NUMBER_RANGE.getValue()
				, BallValueRange.MAX_NUMBER_RANGE.getValue());
		}

		private void checkDuplicateNumber(List<Integer> randomsNumberList, Integer randomNumber) {
			if (!randomsNumberList.contains(randomNumber)) {
				randomsNumberList.add(randomNumber);
			}
		}

		private void createBallArrayUsingRandoms() {
			ballList = new ArrayList<>(BallValueRange.MAX_INDEX_RANGE.getValue() + 1);
			int i = BallValueRange.MIN_INDEX_RANGE.getValue();
			for (int number : createNumberListUsingRandoms()) {
				ballList.add(new BallBuilder().index(i).number(number).build());
				i++;
			}
		}

		public Balls build() {
			createBallArrayUsingRandoms();
			return new Balls(this);
		}
	}
}
