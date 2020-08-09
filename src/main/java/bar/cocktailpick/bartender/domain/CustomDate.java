package bar.cocktailpick.bartender.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomDate {
    private static final String ASIA_SEOUL = "Asia/Seoul";

    private final LocalDate localDate;

    public static CustomDate now() {
        return new CustomDate(LocalDate.now(ZoneId.of(ASIA_SEOUL)));
    }

    public String text(DateTimeFormatter dateTimeFormatter) {
        return localDate.format(dateTimeFormatter);
    }
}
