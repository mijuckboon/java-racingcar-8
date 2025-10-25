package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.RacingResult;
import racingcar.domain.RoundResult;

/**
 * 경주 진행을 담당하는 클래스
 */
public class RacingManager {
    private static final int MIN_VALUE_FOR_MOVE = 4;
    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;
    private static final int MIN_ROUND_NUMBER = 0;
    private static final int MAX_ROUND_NUMBER = 10;

    /**
     * 경주 결과를 초기화하는 메서드
     * @param cars 자동차 목록
     * @return 경주 결과 목록
     */
    public List<RacingResult> createRacingResults(List<Car> cars) {
        List<RacingResult> racingResults = new ArrayList<>();
        for (Car car : cars) {
            String carName = car.getName();
            RacingResult racingResult = new RacingResult(carName);
            racingResults.add(racingResult);
        }
        return racingResults;
    }

    /**
     * 1개의 라운드를 진행하는 메서드
     */
    public void runOneRound(List<RacingResult> racingResults, int roundIndex) {
        for (RacingResult racingResult : racingResults) {
            runOneRoundForOneCar(racingResult, roundIndex);
        }
    }

    private void runOneRoundForOneCar(RacingResult racingResult, int roundIndex) {
        RoundResult roundResult = getRoundResult(roundIndex, racingResult);
        racingResult.saveRoundResult(roundIndex, roundResult);
    }

    private RoundResult getRoundResult(int roundIndex, RacingResult racingResult) {
        int randomValue = chooseRandomValue();
        boolean isMoved = randomValue >= MIN_VALUE_FOR_MOVE;
        int position = racingResult.getLastPosition();
        RoundResult roundResult = new RoundResult(roundIndex, randomValue, isMoved, position);

        if (isMoved) {
           roundResult.moveCar();
        }

        return roundResult;
    }

    private int chooseRandomValue() {
        return Randoms.pickNumberInRange(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
    }

    /**
     * 입력받은 라운드 진행 횟수를 검증하는 메서드
     * @param input 라운드 진행 횟수를 결정할 입력 문자열
     * @return 라운드 진행 횟수
     */
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
