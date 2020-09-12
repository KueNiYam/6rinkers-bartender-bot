package bar.cocktailpick.bartender.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RoleMembersFactory {
    public RoleMembers shuffled() {
        List<Member2> members = Arrays.asList(Member2.values());
        Collections.shuffle(members);

        List<Role2> roles = Arrays.asList(Role2.values());

        List<RoleMember> roleMembers = roleMembersFrom(members, roles);
        return new RoleMembers(roleMembers);
    }

    private List<RoleMember> roleMembersFrom(List<Member2> members, List<Role2> roles) {
        List<RoleMember> roleMembers = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            RoleMember roleMember = new RoleMember(roles.get(i), members.get(i));
            roleMembers.add(roleMember);
        }
        return roleMembers;
    }
}
