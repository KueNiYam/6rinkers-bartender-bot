package bar.cocktailpick.bartender.scheduler;

import bar.cocktailpick.bartender.api.slackhook.SlackHook;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final SlackHook slackHook;

    @Scheduled(cron = "0 11 11 * * TUE-FRI")
    public void lunch() {
        slackHook.lunch();
    }

    @Scheduled(cron = "0 10 0 * * TUE-FRI")
    public void checkInTuesdayToFriday() {
        slackHook.checkIn();
    }

    @Scheduled(cron = "0 13 0 * * MON")
    public void checkInMonday() {
        slackHook.checkIn();
    }

    @Scheduled(cron = "0 18 0 * * MON-FRI")
    public void dinner() {
        slackHook.dinner();
    }

    @Scheduled(cron = "0 15 0 * * MON-FRI")
    public void stretch() {
        slackHook.stretch();
    }
}
