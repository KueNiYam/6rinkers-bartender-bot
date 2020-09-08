package bar.cocktailpick.bartender.domain.member;

import bar.cocktailpick.bartender.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence_gen")
    @SequenceGenerator(name = "member_sequence_gen", sequenceName = "member_sequence")
    private Long id;

    @Column(unique = true)
    private String name;

    @Setter
    private String slackId;

    public Member(String name) {
        this.name = name;
    }
}
