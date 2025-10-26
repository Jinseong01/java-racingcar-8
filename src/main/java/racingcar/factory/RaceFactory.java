package racingcar.factory;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Race;

public class RaceFactory {
    public Race createRace(List<String> carNames) {
        List<Car> cars = createCars(carNames);
        return new Race(cars);
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .toList();
    }
}
