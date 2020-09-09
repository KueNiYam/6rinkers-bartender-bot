package bar.cocktailpick.bartender.webserver.member.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.webserver.member.dto.MemberRequest;
import bar.cocktailpick.bartender.webserver.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberResponse> findAll() {
        List<Member> members = memberRepository.findAll();
        return MemberResponse.listOf(members);
    }

    public Long add(MemberRequest memberRequest) {
        Member member = memberRepository.save(memberRequest.toMember());
        return member.getId();
    }
}
