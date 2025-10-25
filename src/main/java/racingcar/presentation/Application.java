package racingcar.presentation;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.config.AppConfig;
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
        AppConfig appConfig = new AppConfig();
        Application app = appConfig.racingApp();
        app.run();
    }

    /**
     * 프로그램 실행 로직 메서드
     */
    public void run() {
        List<Car> cars = generateCars();
        List<RacingResult> racingResults = racingService.createRacingResults(cars);
        int roundNumber = parseRoundNumber();
        printResult(roundNumber, racingResults);
    }

    private List<Car> generateCars() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();
        return carService.generateCars(inputNames);
    }

    private int parseRoundNumber() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputRound = Console.readLine();
        return racingService.parseAndValidateRoundInput(inputRound);
    }

    private void printResult(int roundNumber, List<RacingResult> racingResults) {
        String result = resultHandler.getResult(roundNumber, racingResults);
        System.out.println(result);
    }
}
