package racingcar.util;

public class TryCountParser {
    public int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수로 정수 이외의 값이 입력되었습니다.");
        }
    }
}
