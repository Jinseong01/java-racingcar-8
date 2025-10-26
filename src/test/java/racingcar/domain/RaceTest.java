package racingcar.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.util.RandomNumberGenerator;

public class RaceTest {

    private ArrayList<Car> cars;

    @BeforeEach
    public void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car("pobi"));
        cars.add(new Car("woni"));
    }

    @Test
    @DisplayName("모든 자동차 이동")
    void playRound() {
        Race race = new Race(cars);

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator() {
            public int generate() {
                return 4; // 무조건 이동
            }
        };
        race.playOneRound(randomNumberGenerator);

        assertThat(cars).allSatisfy(car -> assertThat(car.getDistance()).isEqualTo(1));
    }

    @Test
    @DisplayName("우승자 반환")
    void getWinner() {
        Race race = new Race(cars);

        cars.get(0).moveForward(5);
        cars.get(1).moveForward(3);

        List<String> winners = race.getWinners();
        assertThat(winners).containsExactly("pobi");
    }
}
