package bar.cocktailpick.bartender.domain.rolemembers;

import bar.cocktailpick.bartender.config.LocalDataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@LocalDataJpaTest
class RoleMembersRepositoryTest {

    @Autowired
    RoleMembersRepository roleMembersRepository;

    @Autowired
    RoleMemberRepository roleMemberRepository;

    @Test
    void createWithChildren() {
        RoleMembers roleMembers = new RoleMembers();

        roleMembersRepository.save(roleMembers);

        RoleMember roleMember1 = new RoleMember("😀", "그니", "취사병");
        RoleMember roleMember2 = new RoleMember("😂", "토니", "회의진행자");

        roleMemberRepository.save(roleMember1);
        roleMemberRepository.save(roleMember2);

        roleMembers.add(roleMember1);
        roleMembers.add(roleMember2);

        roleMembersRepository.flush();

        assertAll(
                () -> assertThat(roleMembers.getId()).isNotNull(),
                () -> assertThat(roleMembers.getRoleMembers()).hasSize(2),
                () -> assertThat(roleMember1.getId()).isNotNull(),
                () -> assertThat(roleMember1.getRoleMembers()).isNotNull(),
                () -> assertThat(roleMember2.getId()).isNotNull(),
                () -> assertThat(roleMember2.getRoleMembers()).isNotNull()
        );
    }

    @Test
    void createWithParent() {
        RoleMembers roleMembers = new RoleMembers();

        roleMembersRepository.save(roleMembers);

        RoleMember roleMember1 = new RoleMember("😀", "그니", "취사병");
        RoleMember roleMember2 = new RoleMember("😂", "토니", "회의진행자");

        roleMemberRepository.save(roleMember1);
        roleMemberRepository.save(roleMember2);

        roleMember1.initRoleMembers(roleMembers);
        roleMember2.initRoleMembers(roleMembers);

        roleMembersRepository.flush();

        assertAll(
                () -> assertThat(roleMembers.getId()).isNotNull(),
                () -> assertThat(roleMembers.getRoleMembers()).hasSize(2),
                () -> assertThat(roleMember1.getId()).isNotNull(),
                () -> assertThat(roleMember1.getRoleMembers()).isNotNull(),
                () -> assertThat(roleMember2.getId()).isNotNull(),
                () -> assertThat(roleMember2.getRoleMembers()).isNotNull()
        );
    }
}