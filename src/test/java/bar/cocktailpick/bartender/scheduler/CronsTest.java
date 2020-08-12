package bar.cocktailpick.bartender.scheduler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class CronsTest {
    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Date toDate(String date) {
        try {
            return transFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void lunchTime_IsValid() {
        assertThat(CronSequenceGenerator.isValidExpression(Crons.LUNCH_TIME)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2020-08-11 00:00, 2020-08-11 11:11",
            "2020-08-14 00:00, 2020-08-14 11:11",
            "2020-08-15 00:00, 2020-08-18 11:11"
    })
    public void lunchTime_IsRight(String provided, String expected) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(Crons.LUNCH_TIME);

        assertThat(cronSequenceGenerator.next(toDate(provided))).isEqualTo(toDate(expected));
    }

    @Test
    public void checkInTuesdayToFriday_IsValid() {
        assertThat(CronSequenceGenerator.isValidExpression(Crons.CHECK_IN_TUESDAY_TO_FRIDAY)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2020-08-11 00:00, 2020-08-11 10:00",
            "2020-08-14 00:00, 2020-08-14 10:00",
            "2020-08-15 00:00, 2020-08-18 10:00"
    })
    public void checkInTuesdayToFriday_IsRight(String provided, String expected) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(Crons.CHECK_IN_TUESDAY_TO_FRIDAY);

        assertThat(cronSequenceGenerator.next(toDate(provided))).isEqualTo(toDate(expected));
    }

    @Test
    public void checkInMonday_IsValid() {
        assertThat(CronSequenceGenerator.isValidExpression(Crons.CHECK_IN_MONDAY)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2020-08-10 00:00, 2020-08-10 13:00",
            "2020-08-11 00:00, 2020-08-17 13:00"
    })
    public void checkInMonday_IsRight(String provided, String expected) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(Crons.CHECK_IN_MONDAY);

        assertThat(cronSequenceGenerator.next(toDate(provided))).isEqualTo(toDate(expected));
    }

    @Test
    public void dinnerTime_IsValid() {
        assertThat(CronSequenceGenerator.isValidExpression(Crons.DINNER_TIME)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2020-08-10 00:00, 2020-08-10 18:00",
            "2020-08-14 00:00, 2020-08-14 18:00",
            "2020-08-15 00:00, 2020-08-17 18:00"
    })
    public void dinnerTime_IsRight(String provided, String expected) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(Crons.DINNER_TIME);

        assertThat(cronSequenceGenerator.next(toDate(provided))).isEqualTo(toDate(expected));
    }

    @Test
    public void stretchTime_IsValid() {
        assertThat(CronSequenceGenerator.isValidExpression(Crons.STRETCH_TIME)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2020-08-10 00:00, 2020-08-10 15:00",
            "2020-08-14 00:00, 2020-08-14 15:00",
            "2020-08-15 00:00, 2020-08-17 15:00"
    })
    public void stretchTime_IsRight(String provided, String expected) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(Crons.STRETCH_TIME);

        assertThat(cronSequenceGenerator.next(toDate(provided))).isEqualTo(toDate(expected));
    }
}