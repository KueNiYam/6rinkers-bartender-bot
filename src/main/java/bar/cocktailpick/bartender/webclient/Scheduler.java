package bar.cocktailpick.bartender.webclient;

import bar.cocktailpick.bartender.webclient.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final AlarmService alarmService;

    @Async
    @Scheduled(cron = Crons.LUNCH_TIME)
    public void lunch() {
        alarmService.lunch();
    }

    @Async
    @Scheduled(cron = Crons.CHECK_IN_TUESDAY_TO_FRIDAY)
    public void checkInTuesdayToFriday() {
        alarmService.checkIn();
    }

    @Async
    @Scheduled(cron = Crons.CHECK_IN_MONDAY)
    public void checkInMonday() {
        alarmService.checkIn();
    }

    @Async
    @Scheduled(cron = Crons.DINNER_TIME)
    public void dinner() {
        alarmService.dinner();
    }

    @Async
    @Scheduled(cron = Crons.STRETCH_TIME)
    public void stretch() {
        alarmService.stretch();
    }
}
