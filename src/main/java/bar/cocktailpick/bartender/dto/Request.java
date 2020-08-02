package bar.cocktailpick.bartender.dto;

import bar.cocktailpick.bartender.service.Command;
import lombok.*;

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
    private Double time_stamp;
    private String user_id;
    private String user_name;
    private String text;
    private String trigger_word;

    public boolean is(Command help) {
        return help.is(trigger_word);
    }

    public boolean isBot() {
        return "USLACKBOT".equals(user_id) || "slackbot".equals(user_name);
    }
}
