package bar.cocktailpick.bartender.api.slackhook.dto;

import lombok.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachmentRequest {
    private String color;
    private String pretext;
    private String title;
    private String title_link;
    private String text;
    private String footer;
    private String thumb_url;

    public HookRequest toHookRequest() {
        return new HookRequest(Stream.of(this)
                .collect(Collectors.toList()));
    }
}
