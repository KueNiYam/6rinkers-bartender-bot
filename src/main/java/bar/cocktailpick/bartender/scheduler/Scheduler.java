package bar.cocktailpick.bartender.scheduler;

import bar.cocktailpick.bartender.api.slackhook.SlackHook;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final SlackHook slackHook;

    @Async
    @Scheduled(cron = Crons.LUNCH_TIME)
    public void lunch() {
        slackHook.lunch();
    }

    @Async
    @Scheduled(cron = Crons.CHECK_IN_TUESDAY_TO_FRIDAY)
    public void checkInTuesdayToFriday() {
        slackHook.checkIn();
    }

    @Async
    @Scheduled(cron = Crons.CHECK_IN_MONDAY)
    public void checkInMonday() {
        slackHook.checkIn();
    }

    @Async
    @Scheduled(cron = Crons.DINNER_TIME)
    public void dinner() {
        slackHook.dinner();
    }

    @Async
    @Scheduled(cron = Crons.STRETCH_TIME)
    public void stretch() {
        slackHook.stretch();
    }
}
