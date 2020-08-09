package bar.cocktailpick.bartender.dto;

import lombok.*;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Request {
    private String token;
    private String team_id;
    private String team_domain;
    private String channel_id;
    private String channel_name;
    private String thread_ts;
    private String timestamp;
    private String user_id;
    private String user_name;
    private String text;
    private String trigger_word;

    public boolean isByTrigger(String trigger) {
        return Objects.equals(trigger_word, trigger);
    }

    public boolean isByBot() {
        return "USLACKBOT".equals(user_id) || "slackbot".equals(user_name);
    }
}
