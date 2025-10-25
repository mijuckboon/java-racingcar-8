package racingcar;

/**
 * 클래스 의존성 주입을 담당하는 클래스
 */
public class AppConfig {
    public Application racingApp() {
        CarGenerator carGenerator = new CarGenerator();
        RacingManager racingManager = new RacingManager();
        ResultHandler resultHandler = new ResultHandler(racingManager);
        return new Application(carGenerator, racingManager, resultHandler);
    }
}
