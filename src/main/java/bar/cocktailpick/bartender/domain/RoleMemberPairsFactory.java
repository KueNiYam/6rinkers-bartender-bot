package bar.cocktailpick.bartender.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RoleMemberPairsFactory {
    public RoleMemberPairs create() {
        List<Member> members = Arrays.asList(Member.values());
        Collections.shuffle(members);

        List<Role> roles = Arrays.asList(Role.values());
        List<RoleMemberPair> roleMemberPairs = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            roleMemberPairs.add(new RoleMemberPair(roles.get(i), members.get(i)));
        }
        return new RoleMemberPairs(roleMemberPairs);
    }
}
