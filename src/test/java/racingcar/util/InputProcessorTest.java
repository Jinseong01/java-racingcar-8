package racingcar.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputProcessorTest {

    private InputProcessor inputProcessor;

    @BeforeEach
    void setup() {
        inputProcessor = new InputProcessor(
                new CarNameParser(),
                new CarNameValidator(),
                new TryCountParser(),
                new TryCountValidator()
        );
    }

    @Test
    @DisplayName("자동차 이름 정상값")
    void carNameValid() {
        List<String> carNames = inputProcessor.parseAndValidateCarNames("pobii,woni");
        assertThat(carNames).containsExactly("pobii", "woni");
    }

    @Test
    @DisplayName("빈 문자열/공백 문자열을 입력하면 예외 발생")
    void carNameBlank() {
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(" "))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi,,jun"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(" ,pobi,jun"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("앞뒤에 공백이 있으면 예외 발생")
    void carNameTrim() {
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi ,woni"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(" pobi , woni"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi,woni "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("5자 초과하면 예외 발생")
    void carNameLength() {
        // 정상: 5자
        List<String> carNames = inputProcessor.parseAndValidateCarNames("pobii,woni");
        assertThat(carNames).containsExactly("pobii", "woni");

        // 예외: 6자
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobiii,woni"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되면 예외 발생")
    void carNameDuplicate() {
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi,pobi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최소/최대 범위를 벗어나면 예외 발생")
    void carNameMinMax() {
        // 최소(2) 미만
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi"))
                .isInstanceOf(IllegalArgumentException.class);

        // 최대(10) 초과
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11"))
                .isInstanceOf(IllegalArgumentException.class);

    }


    @Test
    @DisplayName("시도 횟수 정상값")
    void tryCountValid() {
        int tryCount = inputProcessor.parseAndValidateTryCount("5");
        assertThat(tryCount).isEqualTo(5);
    }

    @Test
    @DisplayName("정수가 아니면 예외 발생")
    void tryCountNonInteger() {
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount("A"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount("0.1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최소/최대 범위를 벗어나면 예외 발생")
    void tryCountMinMax() {
        // 최소(1) 미만
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount("0"))
                .isInstanceOf(IllegalArgumentException.class);

        // 최대(100) 초과
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount("101"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
