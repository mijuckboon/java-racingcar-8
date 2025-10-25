package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

/**
 * 경주 진행을 담당하는 클래스
 */
public class RacingManager {
    private static final int MIN_VALUE_FOR_MOVE = 4;
    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;
    private static final int MIN_ROUND_NUMBER = 0;
    private static final int MAX_ROUND_NUMBER = 10;

    public static final int MIN_POSITION = 0;

    /**
     * 1개의 라운드를 진행하는 메서드
     * @param cars 자동차 목록
     */
    public void runOneRound(List<Car> cars) {
        for (Car car : cars) {
            runOneRound(car);
        }
    }

    /**
     * 자동차 한 대에 대해 1개의 라운드를 진행하는 메서드 <br/>
     * 자동차가 움직이는 경우를 결정
     * @param car 자동차
     */
    private void runOneRound(Car car) {
        int randomNumber = chooseRandomValue();
        if (randomNumber >= MIN_VALUE_FOR_MOVE) {
            car.move();
        }
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
