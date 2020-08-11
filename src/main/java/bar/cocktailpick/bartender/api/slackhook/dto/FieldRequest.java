package bar.cocktailpick.bartender.api.slackhook.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FieldRequest {
    private String title;
    private String value;
}
