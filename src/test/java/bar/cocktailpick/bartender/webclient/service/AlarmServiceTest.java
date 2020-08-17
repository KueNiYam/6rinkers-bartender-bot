package bar.cocktailpick.bartender.webclient.service;

import bar.cocktailpick.bartender.api.slackhook.SlackHook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlarmServiceTest {
    private AlarmService alarmService;

    @Mock
    private SlackHook slackHook;

    @BeforeEach
    void setUp() {
        alarmService = new AlarmService(slackHook);
    }

    @AfterEach
    void tearDown() {
        verify(slackHook).send(any());
    }

    @Test
    void lunch() {
        alarmService.lunch();
    }

    @Test
    void checkIn() {
        alarmService.checkIn();
    }

    @Test
    void dinner() {
        alarmService.dinner();
    }

    @Test
    void stretch() {
        alarmService.stretch();
    }
}