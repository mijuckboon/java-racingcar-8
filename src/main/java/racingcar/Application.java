package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.config.AppConfig;
import racingcar.presentation.ResultHandler;
import racingcar.service.CarService;
import racingcar.domain.Car;
import racingcar.domain.RacingResult;
import racingcar.service.RacingService;

/**
 * 경주 프로그램의 진입점 클래스
 * 사용자로부터 차 이름과 횟수를 입력 받아 결과를 출력
 */
public class Application {
    private final CarService carService;
    private final RacingService racingService;
    private final ResultHandler resultHandler;

    public Application(CarService carService, RacingService racingService, ResultHandler resultHandler) {
        this.carService = carService;
        this.racingService = racingService;
        this.resultHandler = resultHandler;
    }

    public static void main(String[] args) {
        Application application = initializeApp();
        application.run();
    }

    private static Application initializeApp() {
        AppConfig appConfig = new AppConfig();
        return appConfig.racingApp();
    }

    /**
     * 프로그램 실행 로직 메서드
     */
    public void run() {
        List<Car> cars = createCars();
        List<RacingResult> racingResults = createRacingResults(cars);
        int totalRounds = parseTotalRounds();
        printResult(totalRounds, racingResults);
    }

    private List<Car> createCars() {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = parseInput();
        return carService.createCars(inputNames);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private String parseInput() {
        return Console.readLine();
    }

    private List<RacingResult> createRacingResults(List<Car> cars) {
        return racingService.createRacingResults(cars);
    }

    private int parseTotalRounds() {
        println("시도할 횟수는 몇 회인가요?");
        String inputRound = parseInput();
        return racingService.parseAndValidateRoundInput(inputRound);
    }

    private void printResult(int totalRounds, List<RacingResult> racingResults) {
        String result = resultHandler.getResult(totalRounds, racingResults);
        println(result);
    }
}
