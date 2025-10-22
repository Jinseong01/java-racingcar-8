package racingcar.util;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {
    public List<String> parse(String input) {
        checkEmpty(input);

        return Arrays.stream(input.split(",")).toList();
    }

    private void checkEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
    }
}
