package bar.cocktailpick.bartender.domain.rolemembers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_member_sequence_gen")
    @SequenceGenerator(name = "role_member_sequence_gen", sequenceName = "role_member_sequence")
    private Long id;

    private String role;

    private String member;

    @ManyToOne
    private RoleMembers roleMembers;

    public RoleMember(Long id, String role, String member) {
        this.id = id;
        this.role = role;
        this.member = member;
    }

    public RoleMember(String role, String member) {
        this(null, role, member);
    }

    public void initRoleMembers(RoleMembers roleMembers) {
        if (this.roleMembers != null) {
            throw new RuntimeException("해당 요소에는 이미 부모 엔터티가 정해져있습니다.");
        }

        this.roleMembers = roleMembers;
        roleMembers.getRoleMembers().add(this);
    }
}
