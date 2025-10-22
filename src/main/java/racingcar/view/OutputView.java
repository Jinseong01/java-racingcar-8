package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {

    private static final String OUTPUT_RESULT = "\n실행 결과";
    private static final String OUTPUT_WINNER = "최종 우승자 : ";

    public void printResultHeader() {
        System.out.println(OUTPUT_RESULT);
    }

    public void printCarMove(Car car) {
        String track = "-".repeat(car.getDistance());
        System.out.println(car.getName() + " : " + track);
    }

    public void printWinner(List<String> winners) {
        String winnerNames = String.join(", ", winners);
        System.out.println(OUTPUT_WINNER + winnerNames);
    }

    public void printBlankLine() {
        System.out.println();
    }
}
