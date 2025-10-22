package racingcar.util;

public class TryCountValidator {

    private static final int MIN_TRY_COUNT = 1;
    private static final int MAX_TRY_COUNT = 100;

    public void validate(int tryCount) {
        validateMin(tryCount);
        validateMax(tryCount);
    }

    private void validateMin(int tryCount) {
        if (tryCount < MIN_TRY_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 최소 " + MIN_TRY_COUNT + " 이상이어야 합니다");
        }
    }

    private void validateMax(int tryCount) {
        if (tryCount > MAX_TRY_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 최대 " + MAX_TRY_COUNT + " 이하이어야 합니다");
        }
    }
}
