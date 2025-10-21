package racingcar;

import racingcar.service.RacingService;
import racingcar.util.CarNameParser;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 이후 수정
        InputView inputView = new InputView();
        String carNamesInput = inputView.readCarName();
        String tryCountInput = inputView.readTryCount();

        OutputView outputView = new OutputView();
        CarNameParser carNameParser = new CarNameParser();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        RacingService racingService = new RacingService(outputView, carNameParser, randomNumberGenerator);
        racingService.startRace(carNamesInput, tryCountInput);
    }
}
