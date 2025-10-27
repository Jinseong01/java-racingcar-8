package racingcar.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RaceTest {

    @Test
    @DisplayName("레이스 생성 시 자동차 목록의 초기화 확인")
    void createRace() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Race race = new Race(cars);

        // then
        assertThat(race.getCars()).hasSize(2);
        assertThat(race.getCars()).extracting(Car::getName)
                .containsExactly("pobi", "woni");
        assertThat(race.getCars()).extracting(Car::getDistance)
                .containsOnly(0);
    }
}
