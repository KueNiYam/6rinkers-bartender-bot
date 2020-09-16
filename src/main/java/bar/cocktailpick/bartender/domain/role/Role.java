package bar.cocktailpick.bartender.domain.role;

import bar.cocktailpick.bartender.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence_gen")
    @SequenceGenerator(name = "role_sequence_gen", sequenceName = "role_sequence")
    private Long id;

    @Column(unique = true)
    private String role;

    @Builder
    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }
}
