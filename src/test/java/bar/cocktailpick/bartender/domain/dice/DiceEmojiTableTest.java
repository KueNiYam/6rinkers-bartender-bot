package bar.cocktailpick.bartender.domain.dice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DiceEmojiTableTest {

    static Stream<Arguments> diceParameters() {
        return Stream.of(
                Arguments.of(new Dice(1), DiceEmojiTable.ONE_TWO_THIRTY.getEmoji()),
                Arguments.of(new Dice(30), DiceEmojiTable.ONE_TWO_THIRTY.getEmoji()),
                Arguments.of(new Dice(31), DiceEmojiTable.THIRTY_ONE_TO_SIXTY.getEmoji()),
                Arguments.of(new Dice(60), DiceEmojiTable.THIRTY_ONE_TO_SIXTY.getEmoji()),
                Arguments.of(new Dice(61), DiceEmojiTable.SIXTY_TO_NINETY_NINE.getEmoji()),
                Arguments.of(new Dice(99), DiceEmojiTable.SIXTY_TO_NINETY_NINE.getEmoji()),
                Arguments.of(new Dice(100), DiceEmojiTable.HUNDRED.getEmoji())
        );
    }

    @ParameterizedTest
    @MethodSource("diceParameters")
    void findEmoji(Dice dice, String emoji) {
        assertThat(DiceEmojiTable.findEmoji(dice)).isEqualTo(emoji);
    }
}