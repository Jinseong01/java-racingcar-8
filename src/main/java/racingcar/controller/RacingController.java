package racingcar.controller;

import java.util.List;
import java.util.Map;
import racingcar.domain.Race;
import racingcar.factory.RaceFactory;
import racingcar.service.RacingService;
import racingcar.util.InputProcessor;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RaceFactory raceFactory;
    private final InputProcessor inputProcessor;
    private final RacingService racingService;

    public RacingController(InputView inputView, OutputView outputView, RaceFactory raceFactory,
                            InputProcessor inputProcessor, RacingService racingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputProcessor = inputProcessor;
        this.raceFactory = raceFactory;
        this.racingService = racingService;
    }

    public void run() {
        List<String> carNames = inputProcessor.parseAndValidateCarNames(inputView.readCarName());
        int tryCount = inputProcessor.parseAndValidateTryCount(inputView.readTryCount());

        Race race = raceFactory.createRace(carNames);

        outputView.printResultHeader();
        for (int i = 0; i < tryCount; i++) {
            Map<String, Integer> roundState = racingService.playOneRound(race);
            roundState.forEach(outputView::printCarState);
            outputView.printBlankLine();
        }

        outputView.printWinner(racingService.getWinners(race));
    }
}
