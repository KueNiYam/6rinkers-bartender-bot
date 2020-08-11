package bar.cocktailpick.bartender.api.slackhook.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachmentRequest {
    private String pretext;
    private String color = "#D00000";
    private String thumb_url;
    private List<FieldRequest> fields;
}
