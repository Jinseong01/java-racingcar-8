package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputProcessorTest {

    private InputProcessor inputProcessor;

    @BeforeEach
    void setUp() {
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
        // given
        String input = "pobii,woni";

        // when
        List<String> carNames = inputProcessor.parseAndValidateCarNames(input);

        // then
        assertThat(carNames).containsExactly("pobii", "woni");
    }

    @Test
    @DisplayName("빈 문자열/공백 문자열 입력 시 예외 발생")
    void carNameBlank() {
        // given & when & then
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
    @DisplayName("앞뒤에 공백이 포함된 이름 입력 시 예외 발생")
    void carNameTrim() {
        // given & when & then
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi ,woni"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(" pobi , woni"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames("pobi,woni "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름의 길이가 5자 초과 시 예외 발생")
    void carNameLength() {
        // given
        String validInput = "pobii,woni";
        String invalidInput = "pobiii,woni";

        // when & then
        List<String> carNames = inputProcessor.parseAndValidateCarNames(validInput);
        assertThat(carNames).containsExactly("pobii", "woni");

        // when & then
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 이름이 중복되는 경우 예외 발생")
    void carNameDuplicate() {
        // given
        String input = "pobi,pobi";

        // when & then
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차의 수가 최소/최대 범위를 벗어나면 예외 발생")
    void carNameMinMax() {
        // given
        String tooFew = "pobi";
        String tooMany = "c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11";

        // when & then
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(tooFew))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateCarNames(tooMany))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("시도 횟수 정상값")
    void tryCountValid() {
        // given
        String input = "5";

        // when
        int tryCount = inputProcessor.parseAndValidateTryCount(input);

        // then
        assertThat(tryCount).isEqualTo(5);
    }

    @Test
    @DisplayName("정수가 아니면 예외 발생")
    void tryCountNonInteger() {
        // given & when & then
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
        // given
        String tooSmall = "0";
        String tooLarge = "101";

        // when & then
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount(tooSmall))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputProcessor.parseAndValidateTryCount(tooLarge))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
