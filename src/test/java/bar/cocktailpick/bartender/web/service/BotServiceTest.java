package bar.cocktailpick.bartender.web.service;

import bar.cocktailpick.bartender.api.SlackApi;
import bar.cocktailpick.bartender.api.dto.UserProfileResponse;
import bar.cocktailpick.bartender.domain.*;
import bar.cocktailpick.bartender.web.dto.BotRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BotServiceTest {
    private BotService botService;

    @Mock
    private RoleMembersFactory shuffledRoleMembersFactory;

    @Mock
    private MemberFactory memberFactory;

    @Mock
    private SlackApi slackApi;

    @Mock
    private BotRequest botRequest;

    @Mock
    private RoleMember roleMember;

    @Mock
    private UserProfileResponse userProfileResponse;

    @BeforeEach
    void setUp() {
        botService = new BotService(shuffledRoleMembersFactory, memberFactory, slackApi);
    }

    @Test
    void serve_WhenReceiveRole() {
        RoleMembers roleMembers = new RoleMembers(Collections.singletonList(roleMember));

        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("역할")).thenReturn(true);
        when(shuffledRoleMembersFactory.shuffled()).thenReturn(roleMembers);
        when(roleMember.getRoleName()).thenReturn("천재");
        when(roleMember.getMemberName()).thenReturn("그니");
        when(roleMember.is(Role.MASTER)).thenReturn(true);
        when(roleMember.is(Role.LEADER)).thenReturn(true);

        assertThat(botService.serve(botRequest).getText()).contains("천재 -> 그니");
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
    void serve_WhenReceiveDraw() {
        when(botRequest.isByTrigger(anyString())).thenReturn(false);
        when(botRequest.isByTrigger("뽑기")).thenReturn(true);
        when(memberFactory.random()).thenReturn(Member.KUENI);

        assertThat(botService.serve(botRequest).getText()).contains("그니");
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