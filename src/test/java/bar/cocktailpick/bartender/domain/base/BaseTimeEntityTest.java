package bar.cocktailpick.bartender.domain.base;

import bar.cocktailpick.bartender.config.LocalDataJpaTest;
import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@LocalDataJpaTest
class BaseTimeEntityTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member("그니");
        memberRepository.save(member);
    }

    @Test
    void getCreatedDate() {
        Member member = memberRepository.findByName("그니")
                .orElseThrow(RuntimeException::new);

        assertThat(member.getCreatedAt()).isNotNull();
    }

    @Test
    void getUpdatedDate() {
        Member member = memberRepository.findByName("그니")
                .orElseThrow(RuntimeException::new);

        assertThat(member.getUpdatedAt()).isNotNull();
    }
}