package bar.cocktailpick.bartender.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomDate {
    private static final String ASIA_SEOUL = "Asia/Seoul";
    private static final String MONTH_DAY = "MM/dd";

    private final LocalDate localDate;

    @Deprecated
    public static CustomDate of(String doubleValue) {
        return new CustomDate(Instant.ofEpochMilli((long) Double.parseDouble(doubleValue))
                .atZone(ZoneId.of(ASIA_SEOUL))
                .toLocalDate());
    }

    public static CustomDate now() {
        return new CustomDate(LocalDate.now(ZoneId.of(ASIA_SEOUL)));
    }

    public String text() {
        return localDate.format(DateTimeFormatter.ofPattern(MONTH_DAY));
    }
}
