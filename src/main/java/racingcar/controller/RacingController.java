package racingcar.controller;

import racingcar.service.RacingService;
import racingcar.view.InputView;

public class RacingController {

    private final InputView inputView;
    private final RacingService racingService;

    public RacingController(InputView inputView, RacingService racingService) {
        this.inputView = inputView;
        this.racingService = racingService;
    }

    public void run() {
        String carNamesInput = inputView.readCarName();
        String tryCountInput = inputView.readTryCount();

        racingService.startRace(carNamesInput, tryCountInput);
    }
}
