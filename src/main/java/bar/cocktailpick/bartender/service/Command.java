package bar.cocktailpick.bartender.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Command {
    HELP("도움"),
    ROLE("역할"),
    REVIEW("리뷰"),
    HELLO("안녕");

    private final String trigger;

    Command(String trigger) {
        this.trigger = trigger;
    }

    public static String commands() {
        return Stream.of(values())
                .map(Command::trigger)
                .collect(Collectors.joining(", "));
    }

    public boolean is(String string) {
        return trigger.equals(string);
    }

    public String trigger() {
        return trigger;
    }
}
