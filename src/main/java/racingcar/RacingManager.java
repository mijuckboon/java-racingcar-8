package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getResult(int roundNumber) {
        StringBuilder resultBuilder = new StringBuilder("실행 결과").append(System.lineSeparator());
        for (int i = 0; i < roundNumber; i++) {
            runOneRound();
            resultBuilder.append(roundResult());
        }
        List<Car> winners = getWinners();
        String winnersInString = winners.stream()
                .map(car -> car.getName())
                .collect(Collectors.joining(", "));
        resultBuilder.append("최종 우승자 : %s".formatted(winnersInString));
        return resultBuilder.toString();
    }

    public void runOneRound() {
        for (Car car : cars) {
            runOneRound(car);
        }
        roundResult();
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

    private StringBuilder roundResult() {
        StringBuilder resultBuilder = new StringBuilder();
        for (Car car : cars) {
            String carName = car.getName();
            int position = car.getPosition();
            String positionMark = "-".repeat(position);
            resultBuilder.append("%s : %s".formatted(carName, positionMark)).append(System.lineSeparator());
        }
        resultBuilder.append(System.lineSeparator());
        return resultBuilder;
    }

    private List<Car> getWinners() {
        List<Car> winners = new ArrayList<>();
        int maxPosition = Car.MIN_POSITION;
        for (Car car : cars) {
            int position = car.getPosition();

            if (position == maxPosition) {
                winners.add(car);
            }

            if (position > maxPosition) {
                maxPosition = position;
                winners = new ArrayList<>(List.of(car));
            }
        }
        return winners;
    }
}
