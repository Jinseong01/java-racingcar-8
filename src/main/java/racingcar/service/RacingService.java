package racingcar.service;

import java.util.List;
import racingcar.domain.Car;
import racingcar.util.CarNameParser;
import racingcar.view.OutputView;

public class RacingService {

    private final OutputView outputView;
    private final CarNameParser carNameParser;

    public RacingService(OutputView outputView, CarNameParser carNameParser) {
        this.outputView = outputView;
        this.carNameParser = carNameParser;
    }

    // 경주를 시작하는 메인 메소드
    public void startRace(String carNamesInput, String tryCountInput) {
        List<Car> cars = createCarList(carNamesInput);
        int tryCount = Integer.parseInt(tryCountInput);

        outputView.printResultHeader();
        runRounds(cars, tryCount);

        List<String> winners = calculateWinners(cars);
        outputView.printWinner(winners);
    }

    // 입력받은 문자열을 파싱하여 자동차 객체 리스트 생성
    private List<Car> createCarList(String carNamesInput) {
        return carNameParser.parseCarNames(carNamesInput).stream()
                .map(Car::new)
                .toList();
    }

    // 입력받은 시도 횟수만큼 경주 라운드를 반복
    private void runRounds(List<Car> cars, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            moveCars(cars);
            System.out.println();
        }
    }

    // 각 자동차마다 무작위 값으로 전진 여부 판단
    private void moveCars(List<Car> cars) {
        for (Car car : cars) {
            int randomNumber = 4;
            car.moveForward(randomNumber);
            outputView.printCarMove(car);
        }
    }

    // 가장 멀리 간 자동차(들)의 이름을 리스트로 반환
    private List<String> calculateWinners(List<Car> cars) {
        int maxDistance = cars.stream().mapToInt(Car::getDistance).max().orElse(0);

        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(Car::getName)
                .toList();
    }
}
