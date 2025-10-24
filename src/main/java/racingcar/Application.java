package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

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

    public void run() {
        List<Car> cars = generateCars();
        int roundNumber = parseRoundNumber();
        printResult(roundNumber, cars);
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

    private void printResult(int roundNumber, List<Car> cars) {
        String result = resultHandler.getResult(roundNumber, cars);
        System.out.println(result);
    }
}
