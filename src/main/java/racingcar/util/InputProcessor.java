package racingcar.util;

import java.util.List;

public class InputProcessor {
    private final CarNameParser carNameParser;
    private final CarNameValidator carNameValidator;
    private final TryCountParser tryCountParser;
    private final TryCountValidator tryCountValidator;

    public InputProcessor(CarNameParser carNameParser, CarNameValidator carNameValidator, TryCountParser tryCountParser,
                          TryCountValidator tryCountValidator) {
        this.carNameParser = carNameParser;
        this.carNameValidator = carNameValidator;
        this.tryCountParser = tryCountParser;
        this.tryCountValidator = tryCountValidator;
    }

    public List<String> parseAndValidateCarNames(String input) {
        List<String> carNames = carNameParser.parse(input);
        carNameValidator.validate(carNames);
        return carNames;
    }

    public int parseAndValidateTryCount(String input) {
        int tryCount = tryCountParser.parse(input);
        tryCountValidator.validate(tryCount);
        return tryCount;
    }
}
