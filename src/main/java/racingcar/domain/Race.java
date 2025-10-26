package racingcar.domain;

import java.util.List;
import racingcar.util.RandomNumberGenerator;

public class Race {

    private final List<Car> cars;

    public Race(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void playOneRound(RandomNumberGenerator randomNumberGenerator) {
        cars.forEach(car -> {
            int randomNumber = randomNumberGenerator.generate();
            car.moveForward(randomNumber);
        });
    }

    public List<String> getWinners() {
        int maxDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(Car::getName)
                .toList();
    }
}
