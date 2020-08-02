package bar.cocktailpick.bartender.rollingRole.dto;

import bar.cocktailpick.bartender.rollingRole.domain.RoleMemberPairs;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RollingRoleResponse {
    private final String text;

    public static RollingRoleResponse of(RoleMemberPairs roleMemberPairs) {
        return new RollingRoleResponse(roleMemberPairs.text());
    }
}
