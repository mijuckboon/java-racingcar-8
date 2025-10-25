package racingcar.config;

import racingcar.presentation.Application;
import racingcar.presentation.ResultHandler;
import racingcar.service.CarService;
import racingcar.service.RacingService;

/**
 * 클래스 의존성 주입을 담당하는 클래스
 */
public class AppConfig {
    public Application racingApp() {
        CarService carService = initializeCarService();
        RacingService racingService = initializeRacingService();
        ResultHandler resultHandler = initializeResultHandler(racingService);
        return initializeApplication(carService, racingService, resultHandler);
    }

    private CarService initializeCarService() {
        return new CarService();
    }

    private RacingService initializeRacingService() {
        return new RacingService();
    }

    private ResultHandler initializeResultHandler(RacingService racingService) {
        return new ResultHandler(racingService);
    }

    private Application initializeApplication(CarService carService, RacingService racingService, ResultHandler resultHandler) {
        return new Application(carService, racingService, resultHandler);
    }
}
