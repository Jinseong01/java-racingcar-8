package racingcar.util;

import java.util.List;

public class CarNameValidator {

    private static final int MIN_CAR_COUNT = 2;
    private static final int MAX_CAR_COUNT = 10;
    private static final int MAX_NAME_LENGTH = 5;

    public void validate(List<String> carNames) {
        validateMinCount(carNames);
        validateMaxCount(carNames);
        validateLength(carNames);
    }

    private void validateMinCount(List<String> carNames) {
        if (carNames.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최소 " + MIN_CAR_COUNT + " 이상이어야 합니다");
        }
    }

    private void validateMaxCount(List<String> carNames) {
        if (carNames.size() > MAX_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최대 " + MAX_CAR_COUNT + " 이하이어야 합니다");
        }
    }

    private void validateLength(List<String> carNames) {
        carNames.forEach(carName -> {
            if (carName.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException("자동차 이름은 최대 " + MAX_NAME_LENGTH + "자까지 가능합니다.");
            }
        });
    }
}
