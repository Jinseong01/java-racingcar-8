package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.util.RandomNumberGenerator;

public class RacingService {

    private final RandomNumberGenerator randomNumberGenerator;

    public RacingService(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Map<String, Integer> playOneRound(Race race) {
        race.getCars().forEach(car -> car.moveForward(randomNumberGenerator.generate()));
        return race.getCars().stream()
                .collect(Collectors.toMap(Car::getName, Car::getDistance));
    }

    public List<String> getWinners(Race race) {
        int maxDistance = race.getCars().stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);

        return race.getCars().stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(Car::getName)
                .toList();
    }
}
