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

    public RoleMemberPair find(Role role) {
        return roleMemberPairs.stream()
                .filter(roleMemberPair -> roleMemberPair.is(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s가 없습니다.", role)));
    }

    public Stream<RoleMemberPair> stream() {
        return roleMemberPairs.stream();
    }
}
