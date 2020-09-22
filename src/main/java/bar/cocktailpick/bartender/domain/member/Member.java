package bar.cocktailpick.bartender.domain.member;

import bar.cocktailpick.bartender.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence_gen")
    @SequenceGenerator(name = "member_sequence_gen", sequenceName = "member_sequence")
    private Long id;

    private String name;

    @Setter
    private String slackId;

    @Builder
    public Member(Long id, String name, String slackId) {
        this.id = id;
        this.name = name;
        this.slackId = slackId;
    }
}
