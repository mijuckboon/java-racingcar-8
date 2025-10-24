package racingcar;

public class AppConfig {
        public Application racingApp() {
            CarGenerator carGenerator = new CarGenerator();
            RacingManager racingManager = new RacingManager();
            ResultHandler resultHandler = new ResultHandler(racingManager);
            return new Application(carGenerator, racingManager, resultHandler);
        }
}
