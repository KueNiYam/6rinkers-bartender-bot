package bar.cocktailpick.bartender.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RoleMembersFactory2 {
    public RoleMembers2 shuffled() {
        List<Member2> members = Arrays.asList(Member2.values());
        Collections.shuffle(members);

        List<Role2> roles = Arrays.asList(Role2.values());

        List<RoleMember2> roleMembers = roleMembersFrom(members, roles);
        return new RoleMembers2(roleMembers);
    }

    private List<RoleMember2> roleMembersFrom(List<Member2> members, List<Role2> roles) {
        List<RoleMember2> roleMembers = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            RoleMember2 roleMember = new RoleMember2(roles.get(i), members.get(i));
            roleMembers.add(roleMember);
        }
        return roleMembers;
    }
}
