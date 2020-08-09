package bar.cocktailpick.bartender.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Command {
    HELP("도움"),
    ROLE("역할"),
    REVIEW("리뷰"),
    HELLO("안녕");

    private final String trigger;

    Command(String trigger) {
        this.trigger = trigger;
    }

    public static List<String> triggers() {
        return Arrays.stream(values())
                .map(command -> command.trigger)
                .collect(Collectors.toList());
    }

    public boolean is(String string) {
        return trigger.equals(string);
    }

}
