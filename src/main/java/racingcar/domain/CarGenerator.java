package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 경주에 참가하는 자동차 생성을 담당하는 클래스
 */
public class CarGenerator {
    private static final String CAR_NAME_SEPARATOR = ",";

    /**
     * 자동차 목록에 대해 이름의 유일성을 검증하는 메서드
     * @param cars 자동차 목록
     */
    public void validateCarsName(List<Car> cars) {
        Set<String> carsNames = new HashSet<>();

        for (Car car : cars) {
            String carName = car.getName();
            if (carsNames.contains(carName)) {
                throw new IllegalArgumentException("차 이름은 중복될 수 없습니다.");
            }

            carsNames.add(car.getName());
        }
    }

    /**
     * 입력 문자열을 바탕으로 자동차 목록을 생성하는 메서드
     * @param input 자동차 이름을 결정하는 입력 문자열
     * @return 자동차 목록
     */
    public List<Car> generateCars(String input) {
        String[] carsNames = input.split(CAR_NAME_SEPARATOR);
        List<Car> cars = new ArrayList<>();
        for (String carName : carsNames) {
            cars.add(new Car(carName));
        }

        validateCarsName(cars);
        return cars;
    }

}
