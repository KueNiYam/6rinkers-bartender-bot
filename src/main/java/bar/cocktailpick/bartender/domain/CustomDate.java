package bar.cocktailpick.bartender.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomDate {
    private final LocalDate localDate;

    public static CustomDate of(String doubleValue) {
        return new CustomDate(Instant.ofEpochMilli((long) Double.parseDouble(doubleValue))
                .atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDate());
    }

    public String text() {
        return localDate.format(DateTimeFormatter.ofPattern("MM/dd"));
    }
}
