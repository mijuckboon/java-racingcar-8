package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 결과를 처리하는 클래스
 */
public class ResultHandler {
    private final RacingManager racingManager;

    public ResultHandler(RacingManager racingManager) {
        this.racingManager = racingManager;
    }

    /**
     * 경주 결과를 반환하는 메서드
     * @param roundNumber 라운드 진행 횟수
     * @param cars 자동차 목록
     * @return 결과 문자열
     */
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

    /**
     * 1개 라운드의 결과를 반환하는 메서드
     * @param cars 자동차 목록
     * @return 결과 StringBuilder 객체
     */
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
