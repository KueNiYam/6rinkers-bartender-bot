package bar.cocktailpick.bartender.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMemberPairs {
    private final List<RoleMemberPair> roleMemberPairs;

    public RoleMemberPairs(List<RoleMemberPair> roleMemberPairs) {
        this.roleMemberPairs = roleMemberPairs;
    }

    public String text() {
        return roleMemberPairs.stream()
                .map(RoleMemberPair::text)
                .collect(Collectors.joining());
    }
}
