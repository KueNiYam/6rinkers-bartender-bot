package bar.cocktailpick.bartender.domain.rolemembers;

import bar.cocktailpick.bartender.domain.rolemembers.dto.RoleMemberCountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "local")
@Sql("/truncate-rolemembers-and-rolemember.sql")
class RoleMemberQueryRepositoryTest {

    @Autowired
    RoleMemberQueryRepository roleMemberQueryRepository;

    @Autowired
    RoleMembersRepository roleMembersRepository;

    @Autowired
    RoleMemberRepository roleMemberRepository;

    @Test
    void countGroupByRoleAndMemberOrderByRoleAscAndMemberAsc() {
        //given
        RoleMembers roleMembers1 = new RoleMembers();
        RoleMembers roleMembers2 = new RoleMembers();
        RoleMembers roleMembers3 = new RoleMembers();

        roleMembersRepository.saveAndFlush(roleMembers1);
        roleMembersRepository.saveAndFlush(roleMembers2);
        roleMembersRepository.saveAndFlush(roleMembers3);

        RoleMember roleMember1 = new RoleMember("취사병", "그니");
        RoleMember roleMember2 = new RoleMember("회의 진행자", "그니");
        RoleMember roleMember3 = new RoleMember("회의 진행자", "토니");

        RoleMember roleMember4 = new RoleMember("취사병", "그니");

        RoleMember roleMember5 = new RoleMember("취사병", "그니");
        RoleMember roleMember6 = new RoleMember("회의 진행자", "토니");

        roleMemberRepository.saveAndFlush(roleMember1);
        roleMemberRepository.saveAndFlush(roleMember2);
        roleMemberRepository.saveAndFlush(roleMember3);
        roleMemberRepository.saveAndFlush(roleMember4);
        roleMemberRepository.saveAndFlush(roleMember5);
        roleMemberRepository.saveAndFlush(roleMember6);

        roleMembers1.add(roleMember1);
        roleMembers1.add(roleMember2);
        roleMembers1.add(roleMember3);

        roleMembers2.add(roleMember4);

        roleMembers3.add(roleMember5);
        roleMembers3.add(roleMember6);

        // when
        List<RoleMemberCountDto> roleMemberCountDtos =
                roleMemberQueryRepository.countGroupByRoleAndMemberOrderByRoleAscAndMemberCountDesc();

        // then
        assertThat(roleMemberCountDtos).isEqualTo(
                Arrays.asList(new RoleMemberCountDto("취사병", "그니", 3L),
                        new RoleMemberCountDto("회의 진행자", "토니", 2L),
                        new RoleMemberCountDto("회의 진행자", "그니", 1L))
        );
    }
}