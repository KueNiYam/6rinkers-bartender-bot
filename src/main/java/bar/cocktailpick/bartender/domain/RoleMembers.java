package bar.cocktailpick.bartender.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
public class RoleMembers {
    private final List<RoleMember> roleMembers;

    public RoleMembers(List<RoleMember> roleMembers) {
        this.roleMembers = roleMembers;
    }

    public RoleMember find(Role role) {
        return roleMembers.stream()
                .filter(roleMember -> roleMember.is(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s가 없습니다.", role)));
    }

    public Stream<RoleMember> stream() {
        return roleMembers.stream();
    }
}
