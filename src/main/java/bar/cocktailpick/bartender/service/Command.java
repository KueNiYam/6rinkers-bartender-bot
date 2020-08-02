package bar.cocktailpick.bartender.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Command {
    HELP("도움", "`도움`"),
    ROLE("역할", "`역할`"),
    REVIEW("리뷰", "`리뷰`"),
    HELLO("안녕", "`안녕`");

    private final String trigger;
    private final String withEffect;

    Command(String trigger, String withEffect) {
        this.trigger = trigger;
        this.withEffect = withEffect;
    }

    public static String commands() {
        return Stream.of(values())
                .map(Command::withEffect)
                .collect(Collectors.joining(", "));
    }

    public boolean is(String string) {
        return trigger.equals(string);
    }

    public String withEffect() {
        return withEffect;
    }
}
