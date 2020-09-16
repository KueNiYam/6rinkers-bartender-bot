package bar.cocktailpick.bartender.domain.rolemembers;

import bar.cocktailpick.bartender.domain.base.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class RoleMembers extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_members_sequence_gen")
    @SequenceGenerator(name = "role_members_sequence_gen", sequenceName = "role_members_sequence")
    private Long id;

    @OneToMany(mappedBy = "roleMembers")
    private List<RoleMember> roleMembers = new ArrayList<>();

    public void add(RoleMember roleMember) {
        roleMember.initRoleMembers(this);
    }
}
