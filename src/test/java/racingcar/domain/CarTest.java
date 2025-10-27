package racingcar.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    @DisplayName("자동차 생성 시 이름과 초기 거리 확인")
    void createCar() {
        // given
        Car car = new Car("pobi");

        // then
        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getDistance()).isZero();
    }

    @Test
    @DisplayName("랜덤값이 기준(4) 이상일 때만 이동")
    void moveByCondition() {
        // given
        Car car = new Car("pobi");

        // when&then
        car.moveForward(3); // 이동 X
        assertThat(car.getDistance()).isZero();

        // when&then
        car.moveForward(4); // 이동 O
        assertThat(car.getDistance()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러 번 전진하면 거리 누적되는지 확인")
    void moveSeveralTimes() {
        // given
        Car car = new Car("pobi");

        // when
        car.moveForward(4);
        car.moveForward(4);
        car.moveForward(4);

        // then
        assertThat(car.getDistance()).isEqualTo(3);
    }
}
