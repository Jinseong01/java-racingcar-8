package racingcar;

import java.util.Arrays;
import java.util.List;
import racingcar.domain.Car;
import racingcar.util.CarNameParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 이후 수정
        InputView inputView = new InputView();
        String carNames = inputView.readCarName();
        String tryCount = inputView.readTryCount();

        CarNameParser parser = new CarNameParser();
        List<String> carNameList = parser.parseCarNames(carNames);

        List<Car> carList = carNameList.stream()
                .map(Car::new)
                .toList();

        Car firstCar = carList.getFirst();
        firstCar.moveForward(5);

        OutputView outputView = new OutputView();
        outputView.printResultHeader();
        carList.forEach(outputView::printCarMove);

        outputView.printWinner(Arrays.asList("pobi", "jun"));
    }
}
