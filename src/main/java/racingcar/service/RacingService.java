package racingcar.service;

import java.util.List;
import racingcar.domain.Car;
import racingcar.util.CarNameParser;
import racingcar.util.RandomNumberGenerator;
import racingcar.util.TryCountParser;
import racingcar.view.OutputView;

public class RacingService {

    private final OutputView outputView;
    private final CarNameParser carNameParser;
    private final TryCountParser tryCountParser;
    private final RandomNumberGenerator randomNumberGenerator;

    public RacingService(OutputView outputView, CarNameParser carNameParser,
                         RandomNumberGenerator randomNumberGenerator, TryCountParser tryCountParser) {
        this.outputView = outputView;
        this.carNameParser = carNameParser;
        this.tryCountParser = tryCountParser;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    // 경주를 시작하는 메인 메소드
    public void startRace(String carNamesInput, String tryCountInput) {
        List<String> carNames = carNameParser.parse(carNamesInput);
        int tryCount = tryCountParser.parse(tryCountInput);

        List<Car> cars = createCars(carNames);

        runRounds(cars, tryCount);

        announceWinners(cars);
    }

    // 자동차 이름 리스트 자동차 객체 리스트 생성
    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .toList();
    }

    // 시도 횟수만큼 경주 라운드 반복
    private void runRounds(List<Car> cars, int tryCount) {
        outputView.printResultHeader();
        for (int i = 0; i < tryCount; i++) {
            moveCars(cars);
            outputView.printBlankLine();
        }
    }

    // 각 자동차마다 전진 시도
    private void moveCars(List<Car> cars) {
        for (Car car : cars) {
            int randomNumber = randomNumberGenerator.getNumber();
            car.moveForward(randomNumber);
            outputView.printCarMove(car);
        }
    }

    // 경주 우승자 출력
    private void announceWinners(List<Car> cars) {
        List<String> winners = findWinners(cars);
        outputView.printWinner(winners);
    }

    // 가장 멀리 간 자동차(들)의 이름 반환
    private List<String> findWinners(List<Car> cars) {
        int maxDistance = findMaxDistance(cars);
        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(Car::getName)
                .toList();
    }

    // 자동차들의 최대 이동 거리 반환
    private int findMaxDistance(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);
    }
}
