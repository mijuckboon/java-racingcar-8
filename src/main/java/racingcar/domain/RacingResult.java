package racingcar.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 경주 결과를 저장하는 객체
 */
public class RacingResult {
    private final String carName;
    private final Map<Integer, RoundResult> roundResults;

    public RacingResult(String carName) {
        this.carName = carName;
        this.roundResults = new HashMap<>();
    }

    /**
     * 라운드 진행 결과를 저장하는 메서드
     * @param roundIndex 라운드 번호
     * @param roundResult 라운드 결과
     */
    public void saveRoundResult(int roundIndex, RoundResult roundResult) {
        roundResults.put(roundIndex, roundResult);
    }

    public int getLastPosition() {
        if (roundResults.isEmpty()) {
            return RoundResult.MIN_POSITION;
        }

        int lastRoundIndex = roundResults.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElse(0);
        return roundResults.get(lastRoundIndex).getPosition();
    }

    public String getCarName() {
        return carName;
    }

    public Map<Integer, RoundResult> getRoundResults() {
        return roundResults;
    }

    public RoundResult getRoundResult(int roundIndex) {
        return roundResults.get(roundIndex);
    }

    public int getPositionAtRound(int roundIndex) {
        return roundResults.get(roundIndex).getPosition();
    }

    public int getPositionAtPreviousRound(int roundIndex) {
        if (roundIndex == 0) {
            return RoundResult.MIN_POSITION;
        }
        return getPositionAtRound(roundIndex - 1);
    }

}
