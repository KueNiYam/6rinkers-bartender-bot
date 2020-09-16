package bar.cocktailpick.bartender.webclient;

import bar.cocktailpick.bartender.webclient.service.AlarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SchedulerTest {

    private Scheduler scheduler;

    @Mock
    private AlarmService alarmService;

    @BeforeEach
    void setUp() {
        scheduler = new Scheduler(alarmService);
    }

    @Test
    void lunch() {
        scheduler.lunch();

        verify(alarmService).lunch();
    }

    @Test
    void checkInTuesdayToFriday() {
        scheduler.checkInTuesdayToFriday();

        verify(alarmService).checkIn();
    }

    @Test
    void checkInMonday() {
        scheduler.checkInMonday();

        verify(alarmService).checkIn();
    }

    @Test
    void dinner() {
        scheduler.dinner();

        verify(alarmService).dinner();
    }

    @Test
    void stretch() {
        scheduler.stretch();

        verify(alarmService).stretch();
    }
}