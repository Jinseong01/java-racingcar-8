package racingcar.controller;

import java.util.List;
import racingcar.domain.Race;
import racingcar.factory.RaceFactory;
import racingcar.util.InputProcessor;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputProcessor inputProcessor;
    private final RaceFactory raceFactory;
    private final RandomNumberGenerator randomNumberGenerator;

    public RacingController(InputView inputView, OutputView outputView, InputProcessor inputProcessor,
                            RaceFactory raceFactory, RandomNumberGenerator randomNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputProcessor = inputProcessor;
        this.raceFactory = raceFactory;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void run() {
        List<String> carNames = inputProcessor.parseAndValidateCarNames(inputView.readCarName());
        int tryCount = inputProcessor.parseAndValidateTryCount(inputView.readTryCount());

        Race race = raceFactory.createRace(carNames);

        outputView.printResultHeader();
        for (int i = 0; i < tryCount; i++) {
            race.playOneRound(randomNumberGenerator);
            race.getCars().forEach(car -> outputView.printCarState(car.getName(), car.getDistance()));
            outputView.printBlankLine();
        }

        outputView.printWinner(race.getWinners());
    }
}
