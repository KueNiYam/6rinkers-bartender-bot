package bar.cocktailpick.bartender.webserver.rolemembers;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.domain.role.RoleRepository;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMemberRepository;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMembers;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMembersRepository;
import bar.cocktailpick.bartender.webserver.rolemembers.dto.RoleMembersResponse;
import bar.cocktailpick.bartender.webserver.rolemembers.service.RoleMembersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleMembersServiceTest {
    private RoleMembersService roleMembersService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMemberRepository roleMemberRepository;

    @Mock
    private RoleMembersRepository roleMembersRepository;

    @BeforeEach
    void setUp() {
        roleMembersService = new RoleMembersService(memberRepository, roleRepository, roleMemberRepository, roleMembersRepository);
    }

    @Test
    void create() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(
                new Member(1L, "그니", null),
                new Member(2L, "토니", null),
                new Member(3L, "두강", null)
        ));

        when(roleRepository.findAll()).thenReturn(Arrays.asList(
                new Role(1L, "취사병"),
                new Role(2L, "회의 진행자"),
                new Role(3L, "서기")
        ));

        RoleMembersResponse result = roleMembersService.create();

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getRoleMemberResponse()).hasSize(3)
        );
    }

    @Test
    void create_WhenMoreMembersThanRoles() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(
                new Member(1L, "그니", null),
                new Member(2L, "토니", null),
                new Member(3L, "두강", null),
                new Member(4L, "작곰", null)
        ));

        when(roleRepository.findAll()).thenReturn(Arrays.asList(
                new Role(1L, "취사병"),
                new Role(2L, "회의 진행자"),
                new Role(3L, "서기")
        ));

        RoleMembersResponse result = roleMembersService.create();

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getRoleMemberResponse()).hasSize(3)
        );
    }

    @Test
    void create_WhenMoreRolesThanMembers_ShouldThrowException() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(
                new Member(1L, "그니", null),
                new Member(2L, "토니", null),
                new Member(3L, "두강", null)
        ));

        when(roleRepository.findAll()).thenReturn(Arrays.asList(
                new Role(1L, "취사병"),
                new Role(2L, "회의 진행자"),
                new Role(3L, "서기"),
                new Role(4L, "지배자")
        ));

        assertThatThrownBy(() -> {
            roleMembersService.create();
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void current() {
        RoleMembers roleMembers = new RoleMembers();

        when(roleMembersRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.of(roleMembers));

        assertThat(roleMembersService.current()).isEqualTo(RoleMembersResponse.of(roleMembers));
    }
}