package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultHandler {
    private final RacingManager racingManager;

    public ResultHandler(RacingManager racingManager) {
        this.racingManager = racingManager;
    }

    public String getResult(int roundNumber, List<Car> cars) {
        StringBuilder resultBuilder = new StringBuilder("실행 결과").append(System.lineSeparator());
        for (int i = 0; i < roundNumber; i++) {
            racingManager.runOneRound(cars);
            resultBuilder.append(buildRoundResult(cars));
        }
        List<Car> winners = getWinners(cars);
        String winnersInString = winners.stream()
                .map(car -> car.getName())
                .collect(Collectors.joining(", "));
        resultBuilder.append("최종 우승자 : %s".formatted(winnersInString));
        return resultBuilder.toString();
    }


    public StringBuilder buildRoundResult(List<Car> cars) {
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

    private List<Car> getWinners(List<Car> cars) {
        List<Car> winners = new ArrayList<>();
        int maxPosition = RacingManager.MIN_POSITION;
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
