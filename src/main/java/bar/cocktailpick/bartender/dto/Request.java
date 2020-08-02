package bar.cocktailpick.bartender.dto;

import bar.cocktailpick.bartender.service.Command;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Request {
    private String token;
    private String teamId;
    private String teamDomain;
    private String channelId;
    private String channelName;
    private String threadTs;
    private Double timeStamp;
    private String userId;
    private String userName;
    private String text;
    private String triggerWord;

    public boolean is(Command help) {
        return help.is(triggerWord);
    }
}
