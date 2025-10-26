package racingcar.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.util.RandomNumberGenerator;

public class RacingServiceTest {

    private ArrayList<Car> cars;
    private Race race;
    private RacingService racingService;

    @BeforeEach
    void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car("pobi"));
        cars.add(new Car("woni"));
        race = new Race(cars);

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator() {
            public int generate() {
                return 4;
            }
        };
        racingService = new RacingService(randomNumberGenerator);
    }

    @Test
    @DisplayName("모든 자동차 이동")
    void playRound() {
        racingService.playOneRound(race);
        assertThat(cars).allSatisfy(car -> assertThat(car.getDistance()).isEqualTo(1));
    }

    @Test
    @DisplayName("우승자 반환")
    void getWinner() {
        cars.get(0).moveForward(5);
        cars.get(1).moveForward(3);

        List<String> winners = racingService.getWinners(race);
        assertThat(winners).containsExactly("pobi");
    }
}
