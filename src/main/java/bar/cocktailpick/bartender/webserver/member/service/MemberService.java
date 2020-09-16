package bar.cocktailpick.bartender.webserver.member.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.webserver.member.dto.SimpleMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public SimpleMemberResponse drawOne() {
        List<Member> members = memberRepository.findAll();

        if (members.isEmpty()) {
            throw new RuntimeException("팀원이 없어서 뽑기가 불가능합니다.");
        }

        Collections.shuffle(members);

        return SimpleMemberResponse.of(members.get(0));
    }

    @Transactional(readOnly = true)
    public List<SimpleMemberResponse> drawTwo() {
        List<Member> members = memberRepository.findAll();

        if (members.size() < 2) {
            throw new RuntimeException("팀원이 2명보다 적어서 뽑기가 불가능합니다.");
        }

        Collections.shuffle(members);

        return SimpleMemberResponse.ofList(members.subList(0, 2));
    }
}
