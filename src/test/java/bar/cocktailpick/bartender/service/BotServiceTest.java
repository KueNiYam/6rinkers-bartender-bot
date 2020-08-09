package bar.cocktailpick.bartender.service;

import bar.cocktailpick.bartender.domain.*;
import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.service.BotService.Command;
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
    private RoleMemberPairsFactory roleMemberPairsFactory;

    @Mock
    private MemberFactory memberFactory;

    @Mock
    private Request request;

    @Mock
    private RoleMemberPair roleMemberPair;

    @BeforeEach
    void setUp() {
        botService = new BotService(roleMemberPairsFactory, memberFactory);
    }

    @Test
    void serve_WhenReceiveRole() {
        RoleMemberPairs roleMemberPairs = new RoleMemberPairs(Collections.singletonList(roleMemberPair));

        when(request.isByTrigger(anyString())).thenReturn(false);
        when(request.isByTrigger("역할")).thenReturn(true);
        when(roleMemberPairsFactory.shuffle()).thenReturn(roleMemberPairs);
        when(roleMemberPair.getRoleName()).thenReturn("천재");
        when(roleMemberPair.getMemberName()).thenReturn("그니");
        when(roleMemberPair.is(Role.MASTER)).thenReturn(true);
        when(roleMemberPair.is(Role.LEADER)).thenReturn(true);

        assertThat(botService.serve(request).getText()).contains("천재 -> 그니");
    }

    @Test
    void serve_WhenReceiveHelp() {
        when(request.isByTrigger(anyString())).thenReturn(false);
        when(request.isByTrigger("도움")).thenReturn(true);

        assertThat(botService.serve(request).getText()).contains(Command.sortedTriggers());
    }

    @Test
    void serve_WhenReceiveReview() {
        when(request.isByTrigger(anyString())).thenReturn(false);
        when(request.isByTrigger("리뷰")).thenReturn(true);
        when(request.getUser_name()).thenReturn("그니");

        assertThat(botService.serve(request).getText()).contains("channel", "리뷰", "그니");
    }

    @Test
    void serve_WhenReceiveDraw() {
        when(request.isByTrigger(anyString())).thenReturn(false);
        when(request.isByTrigger("뽑기")).thenReturn(true);
        when(memberFactory.random()).thenReturn(Member.KUENI);

        assertThat(botService.serve(request).getText()).contains("그니");
    }

    @Test
    void serve_WhenReceiveHello() {
        when(request.isByTrigger(anyString())).thenReturn(false);
        when(request.isByTrigger("안녕")).thenReturn(true);
        when(request.getUser_name()).thenReturn("그니");

        assertThat(botService.serve(request).getText()).contains("안녕하세요", "명령", "도움", "그니");
    }
}