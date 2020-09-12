package bar.cocktailpick.bartender.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class MemberFactory {
    public Member2 random() {
        List<Member2> members = Arrays.asList(Member2.values());
        Collections.shuffle(members);

        return members.get(0);
    }
}
