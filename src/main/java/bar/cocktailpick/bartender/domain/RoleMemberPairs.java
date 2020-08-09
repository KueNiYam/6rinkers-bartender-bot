package bar.cocktailpick.bartender.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
public class RoleMemberPairs {
    private final List<RoleMemberPair> roleMemberPairs;

    public RoleMemberPairs(List<RoleMemberPair> roleMemberPairs) {
        this.roleMemberPairs = roleMemberPairs;
    }

    public Stream<RoleMemberPair> stream() {
        return roleMemberPairs.stream();
    }
}
