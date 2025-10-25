package racingcar.config;

import racingcar.service.CarService;
import racingcar.presentation.ResultHandler;
import racingcar.presentation.Application;
import racingcar.service.RacingService;

/**
 * 클래스 의존성 주입을 담당하는 클래스
 */
public class AppConfig {
    public Application racingApp() {
        CarService carService = new CarService();
        RacingService racingService = new RacingService();
        ResultHandler resultHandler = new ResultHandler(racingService);
        return new Application(carService, racingService, resultHandler);
    }
}
