package racingcar.util;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {
    public List<String> parse(String input) {
        checkBlank(input);

        List<String> carNames = Arrays.stream(input.split(",")).toList();

        checkEachName(carNames);

        return carNames;
    }

    private void checkEachName(List<String> carNames) {
        carNames.forEach(carName -> {
            checkBlank(carName);
            checkTrim(carName);
        });
    }

    private void checkBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("자동차 이름 입력은 필수 입력 사항입니다.");
        }
    }

    private void checkTrim(String input) {
        if (!input.equals(input.trim())) {
            throw new IllegalArgumentException("자동차 이름에 앞뒤 공백은 허용되지 않습니다.");
        }
    }
}
