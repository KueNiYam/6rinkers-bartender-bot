package bar.cocktailpick.bartender.webserver.adminbot.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.domain.role.RoleRepository;
import bar.cocktailpick.bartender.webserver.adminbot.dto.AdminBotResponse;
import bar.cocktailpick.bartender.webserver.common.dto.BotRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminBotServiceTest {
    private AdminBotService adminBotService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BotRequest botRequest;

    @BeforeEach
    void setUp() {
        adminBotService = new AdminBotService(memberRepository, roleRepository);
    }

    @Test
    void serve_WhenReceiveHelp() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.HELP.getTrigger())).thenReturn(true);

        assertThat(adminBotService.serve(botRequest).getText()).contains(AdminBotService.Command.sortedTriggers());
    }

    @Test
    void serve_WhenReceiveAllMembers() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ALL_MEMBERS.getTrigger())).thenReturn(true);

        when(memberRepository.findAll()).thenReturn(Arrays.asList(
                new Member(1L, "그니", null),
                new Member(2L, "토니", "u1234567")
        ));

        assertThat(adminBotService.serve(botRequest).getText()).contains("1", "그니", "null", "2", "토니", "u1234567");
    }

    @Test
    void serve_WhenAddMember() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_MEMBER.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_MEMBER.getTrigger() + " 그니");

        when(memberRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(memberRepository.save(any())).thenReturn(new Member(1L, "그니", null));

        assertThat(adminBotService.serve(botRequest).getText()).contains("1", "그니", "null");
    }

    @Test
    void serve_WhenAddMember_Null_ShouldThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_MEMBER.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_MEMBER.getTrigger());

        assertThat(adminBotService.serve(botRequest).getText()).isEqualTo(AdminBotResponse.ofAddMemberHelp().getText());
    }

    @Test
    void serve_WhenAddMember_Blank_ShouldThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_MEMBER.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_MEMBER.getTrigger() + " ");

        assertThat(adminBotService.serve(botRequest).getText()).isEqualTo(AdminBotResponse.ofAddMemberHelp().getText());
    }

    @Test
    void serve_WhenAddMember_ExistMember_ShouldThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_MEMBER.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_MEMBER.getTrigger() + " 그니");

        when(memberRepository.findByName(anyString())).thenReturn(Optional.of(new Member(1L, "그니", null)));

        assertThat(adminBotService.serve(botRequest).getText()).isEqualTo(AdminBotResponse.ofMemberExistError().getText());
    }

    @Test
    void serve_WhenReceiveAllRoles() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ALL_ROLES.getTrigger())).thenReturn(true);

        when(roleRepository.findAll()).thenReturn(Arrays.asList(
                new Role(1L, "취사병"),
                new Role(2L, "회의 진행자")
        ));

        assertThat(adminBotService.serve(botRequest).getText()).contains("1", "취사병", "2", "회의 진행자");
    }

    @Test
    void serve_WhenAddRole() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_ROLE.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_ROLE.getTrigger() + " 😇 취사병");

        when(roleRepository.findByRole(anyString())).thenReturn(Optional.empty());
        when(roleRepository.save(any())).thenReturn(new Role(1L, "😇 취사병"));

        assertThat(adminBotService.serve(botRequest).getText()).contains("1", "😇 취사병");
    }

    @Test
    void serve_WhenAddRole_Null_ShouldThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_ROLE.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_ROLE.getTrigger());

        assertThat(adminBotService.serve(botRequest).getText()).isEqualTo(AdminBotResponse.ofAddRoleHelp().getText());
    }

    @Test
    void serve_WhenAddRole_Blank_ShouldThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_ROLE.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_ROLE.getTrigger() + " ");

        assertThat(adminBotService.serve(botRequest).getText()).isEqualTo(AdminBotResponse.ofAddRoleHelp().getText());
    }

    @Test
    void serve_WhenAddRole_ExistMember_ShouldThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(AdminBotService.Command.ADD_ROLE.getTrigger())).thenReturn(true);

        when(botRequest.getText()).thenReturn(AdminBotService.Command.ADD_ROLE.getTrigger() + " 😇 취사병");

        when(roleRepository.findByRole(anyString())).thenReturn(Optional.of(new Role(1L, "😇 취사병")));

        assertThat(adminBotService.serve(botRequest).getText()).isEqualTo(AdminBotResponse.ofRoleExistError().getText());
    }
}