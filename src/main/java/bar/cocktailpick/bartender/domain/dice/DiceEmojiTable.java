package bar.cocktailpick.bartender.domain.dice;

import bar.cocktailpick.bartender.util.EmojiUtils;

import java.util.Arrays;

public enum DiceEmojiTable {
    ONE_TWO_THIRTY(30, "ㅠㅠ " + EmojiUtils.CRY),
    THIRTY_ONE_TO_SIXTY(60, "ㅋㅋ " + EmojiUtils.JOY),
    SIXTY_TO_NINETY_NINE(99, "ㅎㅎ " + EmojiUtils.HEART_EYES),
    HUNDRED(100, "!!!!! " + EmojiUtils.STAR_STRUCK + EmojiUtils.STAR_STRUCK + EmojiUtils.STAR_STRUCK);

    private final int max;
    private final String emoji;

    DiceEmojiTable(int max, String emoji) {
        this.max = max;
        this.emoji = emoji;
    }

    public static String findEmoji(Dice dice) {
        return Arrays.stream(values())
                .filter((column) -> dice.isOrBelow(column.max))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이모지를 찾을 수 없습니다."))
                .emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
