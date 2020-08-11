package bar.cocktailpick.bartender.api.slackhook.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HookRequest {
    List<AttachmentRequest> attachments;
}
