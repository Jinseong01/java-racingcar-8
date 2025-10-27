package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @DisplayName("한 라운드 진행 시 자동차 이동과 상태 확인")
    void playOneRound() {
        // when
        Map<String, Integer> result = racingService.playOneRound(race);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get("pobi")).isEqualTo(1);
        assertThat(result.get("woni")).isEqualTo(1);
    }

    @Test
    @DisplayName("여러 라운드 진행 시 거리 누적")
    void playMultipleRounds() {
        // when
        racingService.playOneRound(race);
        racingService.playOneRound(race);
        Map<String, Integer> result = racingService.playOneRound(race);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get("pobi")).isEqualTo(3);
        assertThat(result.get("woni")).isEqualTo(3);
    }

    @Test
    @DisplayName("단독 우승 결정 확인")
    void getOneWinner() {
        // given
        cars.get(0).moveForward(5);
        cars.get(1).moveForward(3);

        // when
        List<String> winners = racingService.getWinners(race);

        // then
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    @DisplayName("공동 우승 결정 확인")
    void getAllWinners() {
        // when
        List<String> winners = racingService.getWinners(race);

        // then
        assertThat(winners).hasSize(2)
                .containsExactly("pobi", "woni");
    }
}
