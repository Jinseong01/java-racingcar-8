package racingcar.util;

import java.util.List;

public class CarNameValidator {

    private static final int MIN_CAR = 2;
    private static final int MAX_CAR = 10;

    public void validate(List<String> carNames) {
        validateMin(carNames);
        validateMax(carNames);
    }

    private void validateMin(List<String> carNames) {
        if (carNames.size() < MIN_CAR) {
            throw new IllegalArgumentException("자동차는 최소 " + MIN_CAR + " 이상이어야 합니다");
        }
    }

    private void validateMax(List<String> carNames) {
        if (carNames.size() > MAX_CAR) {
            throw new IllegalArgumentException("자동차는 최대 " + MAX_CAR + " 이하이어야 합니다");
        }
    }
}
