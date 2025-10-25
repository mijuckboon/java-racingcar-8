package racingcar.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

class CarServiceTest {

    @Test
    void validateCarsNameFailsByDuplicateName() {
        CarService carService = new CarService();
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("pobi"));

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.validateCarsName(cars));
    }

    @Test
    void createCarsTest() {
        CarService carService = new CarService();
        // given
        String input = "pobi,woni";

        // when
        List<Car> cars = carService.createCars(input);

        // then
        Assertions.assertEquals(2, cars.size());
        Assertions.assertEquals("pobi", cars.get(0).getName());
        Assertions.assertEquals("woni", cars.get(1).getName());
    }

    @Test
    void createCarsFailsByBlankName() {
        CarService carService = new CarService();
        // given
        String input = "pobi, ";

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> carService.createCars(input));
    }
}