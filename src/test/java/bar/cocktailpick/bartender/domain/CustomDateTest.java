package bar.cocktailpick.bartender.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomDateTest {
    @DisplayName("학습 테스트")
    @Test
    void doubleToLocalDate() {
        LocalDate localDate = Instant.ofEpochMilli((long) 1504640775.000005)
                .atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDate();
        System.out.println(localDate);
    }

    @Test
    void text() {
        CustomDate customDate = new CustomDate(LocalDate.of(2020, Month.AUGUST, 3));
        assertThat(customDate.text(DateTimeFormatter.ofPattern("MM/dd"))).isEqualTo("08/03");
    }
}