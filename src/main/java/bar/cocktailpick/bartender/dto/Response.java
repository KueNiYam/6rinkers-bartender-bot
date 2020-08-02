package bar.cocktailpick.bartender.dto;

import bar.cocktailpick.bartender.domain.RoleMemberPairs;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Response {
    private final String color = "#A00000";
    private final String title = "테스트";
    private final String text;

    public static Response of(RoleMemberPairs roleMemberPairs) {
        return new Response(roleMemberPairs.text());
    }
}
