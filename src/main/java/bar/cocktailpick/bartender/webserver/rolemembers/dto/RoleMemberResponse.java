package bar.cocktailpick.bartender.webserver.rolemembers.dto;

import bar.cocktailpick.bartender.domain.rolemembers.RoleMember;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RoleMemberResponse {
    private final String role;
    private final String member;

    public static RoleMemberResponse of(RoleMember roleMember) {
        return new RoleMemberResponse(roleMember.getRole(), roleMember.getMember());
    }

    public String toText() {
        return role + "->" + member;
    }
}
