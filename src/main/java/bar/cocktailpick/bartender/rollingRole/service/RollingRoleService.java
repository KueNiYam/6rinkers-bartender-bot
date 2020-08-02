package bar.cocktailpick.bartender.rollingRole.service;

import bar.cocktailpick.bartender.rollingRole.domain.RoleMemberPairsFactory;
import bar.cocktailpick.bartender.rollingRole.dto.RollingRoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class RollingRoleService {
    private final RoleMemberPairsFactory roleMemberPairsFactory;

    public RollingRoleResponse rollingRole() {
        return RollingRoleResponse.of(roleMemberPairsFactory.create());
    }
}
