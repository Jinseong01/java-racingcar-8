package racingcar.util;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {
    public List<String> parse(String input) {
        return Arrays.stream(input.split(",")).toList();
    }
}
