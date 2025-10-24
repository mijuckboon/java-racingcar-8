package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();
        CarGenerator carGenerator = new CarGenerator();
        List<Car> cars = carGenerator.generateCars(inputNames);
        RacingManager racingManager = new RacingManager(cars);
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputRound = Console.readLine();
        int roundNumber = racingManager.validateRoundInput(inputRound);
        String result = racingManager.getResult(roundNumber);
        System.out.println(result);
    }

 /*   """
    실행 결과
    pobi : -
    woni :
    jun : -

    pobi : --
    woni : -
    jun : --

    pobi : ---
    woni : --
    jun : ---

    pobi : ----
    woni : ---
    jun : ----

    pobi : -----
    woni : ----
    jun : -----

    최종 우승자 : pobi, jun
    """
*/
}
