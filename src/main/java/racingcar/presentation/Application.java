package racingcar.presentation;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.config.AppConfig;
import racingcar.domain.CarGenerator;
import racingcar.domain.Car;
import racingcar.domain.RacingResult;
import racingcar.service.RacingManager;

/**
 * 경주 프로그램의 진입점 클래스
 * 사용자로부터 차 이름과 횟수를 입력 받아 결과를 출력
 */
public class Application {
    private final CarGenerator carGenerator;
    private final RacingManager racingManager;
    private final ResultHandler resultHandler;

    public Application(CarGenerator carGenerator, RacingManager racingManager, ResultHandler resultHandler) {
        this.carGenerator = carGenerator;
        this.racingManager = racingManager;
        this.resultHandler = resultHandler;
    }

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Application app = appConfig.racingApp();
        app.run();
    }

    /**
     * 프로그램 실행 로직 메서드
     */
    public void run() {
        List<Car> cars = generateCars();
        List<RacingResult> racingResults = racingManager.createRacingResults(cars);
        int roundNumber = parseRoundNumber();
        printResult(roundNumber, racingResults);
    }

    private List<Car> generateCars() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();
        return carGenerator.generateCars(inputNames);
    }

    private int parseRoundNumber() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputRound = Console.readLine();
        return racingManager.validateRoundInput(inputRound);
    }

    private void printResult(int roundNumber, List<RacingResult> racingResults) {
        String result = resultHandler.getResult(roundNumber, racingResults);
        System.out.println(result);
    }
}
