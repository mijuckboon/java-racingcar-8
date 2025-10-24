package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RacingManager {
    private static final int MIN_VALUE_FOR_MOVE = 4;
    private static final int MAX_VALUE_FOR_STOP = 3;
    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;
    private static final int MIN_ROUND_NUMBER = 0;
    private static final int MAX_ROUND_NUMBER = 10;

    private final List<Car> cars;

    public RacingManager(List<Car> cars) {
        this.cars = cars;
    }

    public void runOneRound() {
        for (Car car : cars) {
            runOneRound(car);
        }
    }

    private void runOneRound(Car car) {
        int randomNumber = chooseRandomValue();
        if (randomNumber >= MIN_VALUE_FOR_MOVE) {
            car.move();
        }
    }

    private int chooseRandomValue() {
        return Randoms.pickNumberInRange(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
    }

    public int validateRoundInput(String input) {
        int roundNumber = Integer.parseInt(input);
        validateRoundNumber(roundNumber);
        return roundNumber;
    }

    private void validateRoundNumber(int roundNumber) {
        if (roundNumber < MIN_ROUND_NUMBER || roundNumber > MAX_ROUND_NUMBER) {
            throw new IllegalArgumentException("시도할 횟수는 %d 이상, %d 이하의 수여야 합니다.".formatted(
                    MIN_ROUND_NUMBER, MAX_ROUND_NUMBER
            ));
        }
    }
}
