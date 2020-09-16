package bar.cocktailpick.bartender.webserver.bot.service;

import bar.cocktailpick.bartender.api.slackapi.SlackApi;
import bar.cocktailpick.bartender.api.slackapi.dto.UserProfileResponse;
import bar.cocktailpick.bartender.domain.MemberFactory2;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMembers;
import bar.cocktailpick.bartender.webserver.bot.dto.BotResponse;
import bar.cocktailpick.bartender.webserver.common.dto.BotRequest;
import bar.cocktailpick.bartender.webserver.member.dto.SimpleMemberResponse;
import bar.cocktailpick.bartender.webserver.member.service.MemberService;
import bar.cocktailpick.bartender.webserver.rolemembers.dto.RoleMemberResponse;
import bar.cocktailpick.bartender.webserver.rolemembers.dto.RoleMembersResponse;
import bar.cocktailpick.bartender.webserver.rolemembers.service.RoleMembersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BotServiceTest {
    private BotService botService;

    @Mock
    private MemberFactory2 memberFactory;

    @Mock
    private SlackApi slackApi;

    @Mock
    private RoleMembersService roleMembersService;

    @Mock
    private MemberService memberService;

    @Mock
    private BotRequest botRequest;

    @Mock
    private UserProfileResponse userProfileResponse;

    @BeforeEach
    void setUp() {
        botService = new BotService(slackApi, roleMembersService, memberService);
    }

    @Test
    void serve_WhenReceiveCreateRole() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("새로운 역할")).thenReturn(true);

        when(roleMembersService.create()).thenReturn(new RoleMembersResponse(
                Collections.singletonList(new RoleMemberResponse("취사병", "그니"))
                , null));

        assertThat(botService.serve(botRequest).getText()).contains("취사병", "그니");
    }

    @Test
    void serve_WhenReceiveCreateRoleAndNoRoles() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("새로운 역할")).thenReturn(true);

        when(roleMembersService.create()).thenReturn(new RoleMembersResponse(
                Collections.emptyList(), null));

        assertThat(botService.serve(botRequest).getText())
                .isEqualTo(BotResponse.ofRoleMembersNoContent().getText());
    }

    @Test
    void serve_WhenReceiveCreateRoleAndRoleMembersServiceThrowException() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("새로운 역할")).thenReturn(true);

        when(roleMembersService.create()).thenThrow(RuntimeException.class);

        assertThat(botService.serve(botRequest).getText())
                .isEqualTo(BotResponse.ofError(new RuntimeException()).getText());
    }

    @Test
    void serve_WhenReceiveCurrentRole() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("현재 역할")).thenReturn(true);

        RoleMembers roleMembers = new RoleMembers();
        when(roleMembersService.current()).thenReturn(RoleMembersResponse.of(roleMembers));

        assertThat(botService.serve(botRequest).getText()).isNotNull();
    }

    @Test
    void serve_WhenReceiveHelp() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("도움")).thenReturn(true);

        assertThat(botService.serve(botRequest).getText()).contains(BotService.Command.sortedTriggers());
    }

    @Test
    void serve_WhenReceiveReview() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("리뷰")).thenReturn(true);
        when(botRequest.getUser_id()).thenReturn("u12345678");
        when(slackApi.getProfile("u12345678")).thenReturn(userProfileResponse);
        when(userProfileResponse.isOk()).thenReturn(true);
        when(userProfileResponse.displayName()).thenReturn("그니");

        assertThat(botService.serve(botRequest).getText()).contains("channel", "리뷰", "그니");
    }

    @Test
    void serve_WhenReceiveDrawOne() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(BotService.Command.DRAW_ONE.getTrigger())).thenReturn(true);

        when(memberService.drawOne()).thenReturn(new SimpleMemberResponse("그니"));

        assertThat(botService.serve(botRequest).getText()).contains("그니");
    }

    @Test
    void serve_WhenReceiveDrawTwo() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger(BotService.Command.DRAW_TWO.getTrigger())).thenReturn(true);

        when(memberService.drawTwo()).thenReturn(Arrays.asList(
                new SimpleMemberResponse("그니"),
                new SimpleMemberResponse("토니"))
        );

        assertThat(botService.serve(botRequest).getText()).contains("그니", "토니");
    }

    @Test
    void serve_WhenReceiveHello() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("안녕")).thenReturn(true);
        when(botRequest.getUser_id()).thenReturn("u12345678");
        when(slackApi.getProfile("u12345678")).thenReturn(userProfileResponse);
        when(userProfileResponse.isOk()).thenReturn(true);
        when(userProfileResponse.displayName()).thenReturn("그니");

        assertThat(botService.serve(botRequest).getText()).contains("안녕하세요", "명령", "도움", "그니");
    }
}