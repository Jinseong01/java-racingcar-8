package racingcar;

import racingcar.controller.RacingController;
import racingcar.factory.RaceFactory;
import racingcar.util.CarNameParser;
import racingcar.util.CarNameValidator;
import racingcar.util.InputProcessor;
import racingcar.util.RandomNumberGenerator;
import racingcar.util.TryCountParser;
import racingcar.util.TryCountValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        CarNameParser carNameParser = new CarNameParser();
        TryCountParser tryCountParser = new TryCountParser();
        CarNameValidator carNameValidator = new CarNameValidator();
        TryCountValidator tryCountValidator = new TryCountValidator();
        InputProcessor inputProcessor = new InputProcessor(carNameParser, carNameValidator, tryCountParser,
                tryCountValidator);

        RaceFactory raceFactory = new RaceFactory();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        RacingController racingController = new RacingController(inputView, outputView, inputProcessor, raceFactory,
                randomNumberGenerator);
        racingController.run();
    }
}
