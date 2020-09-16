package bar.cocktailpick.bartender.webserver.member.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void drawOne() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(
                new Member(1L, "그니", null),
                new Member(2L, "토니", null)));

        assertThat(memberService.drawOne()).isNotNull();
    }

    @Test
    void drawOne_WhenNoMember_ShouldThrowException() {
        when(memberRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> memberService.drawOne())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void drawTwo() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(
                new Member(1L, "그니", null),
                new Member(2L, "토니", null)));

        assertThat(memberService.drawTwo()).hasSize(2);
    }

    @Test
    void drawTwo_WhenMemberLessThanTwo_ShouldThrowException() {
        when(memberRepository.findAll()).thenReturn(Collections.singletonList(
                new Member(1L, "그니", null)));

        assertThatThrownBy(() -> memberService.drawTwo())
                .isInstanceOf(RuntimeException.class);
    }
}