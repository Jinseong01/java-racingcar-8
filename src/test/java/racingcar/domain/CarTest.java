package racingcar.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    @DisplayName("랜덤값이 기준 이상일 때만 이동")
    void move() {
        Car car = new Car("pobi");

        car.moveForward(3); // 이동 X
        assertThat(car.getDistance()).isZero();

        car.moveForward(4); // 이동 O
        assertThat(car.getDistance()).isEqualTo(1);
    }
}
