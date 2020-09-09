package bar.cocktailpick.bartender.webserver.member.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.webserver.member.dto.MemberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void findAll() {
        when(memberRepository.findAll()).thenReturn(new ArrayList<>());

        assertThat(memberService.findAll()).isNotNull();
    }

    @Test
    void add() {
        MemberRequest memberRequest = new MemberRequest("그니", null);

        Member member = Member.builder()
                .id(1L)
                .name("그니")
                .build();

        when(memberRepository.save(any())).thenReturn(member);

        assertThat(memberService.add(memberRequest)).isEqualTo(1L);
    }
}