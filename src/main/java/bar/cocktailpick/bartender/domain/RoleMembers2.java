package bar.cocktailpick.bartender.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
public class RoleMembers2 {
    private final List<RoleMember2> roleMembers;

    public RoleMembers2(List<RoleMember2> roleMembers) {
        this.roleMembers = roleMembers;
    }

    public RoleMember2 find(Role2 role) {
        return roleMembers.stream()
                .filter(roleMember -> roleMember.is(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s가 없습니다.", role)));
    }

    public Stream<RoleMember2> stream() {
        return roleMembers.stream();
    }
}
