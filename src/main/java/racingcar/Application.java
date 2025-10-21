package racingcar;

import racingcar.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 이후 수정
        InputView inputView = new InputView();
        String carName = inputView.readCarName();
        String tryCount = inputView.readTryCount();
    }
}
