package racingcar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarGenerator {
    private static final String CAR_NAME_SEPARATOR = ",";

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
