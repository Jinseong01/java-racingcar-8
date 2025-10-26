package racingcar.view;

import java.util.List;

public class OutputView {

    private static final String OUTPUT_RESULT_MESSAGE = "\n실행 결과";
    private static final String OUTPUT_WINNER_MESSAGE = "최종 우승자 : ";

    public void printResultHeader() {
        System.out.println(OUTPUT_RESULT_MESSAGE);
    }

    public void printCarState(String carName, int carDistance) {
        String track = "-".repeat(carDistance);
        System.out.println(carName + " : " + track);
    }

    public void printWinner(List<String> winners) {
        String winnerNames = String.join(", ", winners);
        System.out.println(OUTPUT_WINNER_MESSAGE + winnerNames);
    }

    public void printBlankLine() {
        System.out.println();
    }
}
