package racingcar.presentation;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.RacingResult;
import racingcar.domain.RoundResult;
import racingcar.service.RacingService;

/**
 * 결과를 처리하는 클래스
 */
public class ResultHandler {
    private final RacingService racingService;

    public ResultHandler(RacingService racingService) {
        this.racingService = racingService;
    }

    /**
     * 경주 결과 문자열을 반환하는 메서드
     * @param totalRounds 라운드 진행 횟수
     * @param racingResults 자동차별 경주 결과 목록
     * @return 결과 문자열
     */
    public String getResult(int totalRounds, List<RacingResult> racingResults) {
        StringBuilder resultBuilder = new StringBuilder("실행 결과").append(System.lineSeparator());
        for (int roundIndex = 1; roundIndex <= totalRounds; roundIndex++) {
            racingService.runOneRound(racingResults, roundIndex);
            resultBuilder.append(buildRoundResult(racingResults, roundIndex));
        }
        List<String> winners = getWinners(racingResults);
        resultBuilder.append(getWinnersInString(winners));
        return resultBuilder.toString();
    }

    private String getWinnersInString(List<String> winners) {
        String winnersInString = String.join(", ", winners);
        return "최종 우승자 : %s".formatted(winnersInString);
    }

    /**
     * 1개 라운드의 결과를 반환하는 메서드
     * @param racingResults 경주 결과 배열
     * @param roundIndex 라운드 번호
     * @return StringBuilder 객체
     */
    public StringBuilder buildRoundResult(List<RacingResult> racingResults, int roundIndex) {
        StringBuilder resultBuilder = new StringBuilder();
        for (RacingResult racingResult : racingResults) {
            RoundResult roundResult = racingResult.getRoundResult(roundIndex);
            String carName = racingResult.getCarName();
            String positionMark = "-".repeat(roundResult.getPosition());
            resultBuilder.append("%s : %s".formatted(carName, positionMark))
                    .append(System.lineSeparator());
        }
        resultBuilder.append(System.lineSeparator());
        return resultBuilder;
    }

    private List<String> getWinners(List<RacingResult> racingResults) {
        List<String> winners = new ArrayList<>();
        int maxPosition = RoundResult.MIN_POSITION;
        for (RacingResult racingResult : racingResults) {
            int position = racingResult.getLastPosition();
            String carName = racingResult.getCarName();
            if (position == maxPosition) {
                winners.add(carName);
            }
            if (position > maxPosition) {
                maxPosition = position;
                winners.clear();
                winners.add(carName);
            }
        }
        return winners;
    }
}
