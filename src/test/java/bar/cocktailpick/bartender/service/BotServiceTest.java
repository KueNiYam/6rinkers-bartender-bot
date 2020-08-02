package bar.cocktailpick.bartender.service;

import bar.cocktailpick.bartender.domain.RoleMemberPairs;
import bar.cocktailpick.bartender.domain.RoleMemberPairsFactory;
import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BotServiceTest {
    private BotService botService;

    @Mock
    private Request request;

    @Mock
    private RoleMemberPairsFactory roleMemberPairsFactory;

    @Mock
    private RoleMemberPairs roleMemberPairs;

    @BeforeEach
    void setUp() {
        botService = new BotService(roleMemberPairsFactory);
    }

    @Test
    void serve_WhenReceiveRole() {
        when(request.is(any())).thenReturn(false);
        when(request.is(Command.ROLE)).thenReturn(true);
        when(roleMemberPairsFactory.create()).thenReturn(roleMemberPairs);
        when(roleMemberPairs.text()).thenReturn("천재 -> 그니");

        assertThat(botService.serve(request)).isEqualTo(new Response("천재 -> 그니"));
    }

    @Test
    void serve_WhenReceiveHelp() {
        when(request.is(any())).thenReturn(false);
        when(request.is(Command.HELP)).thenReturn(true);

        assertThat(botService.serve(request)).isEqualTo(new Response(Command.commands()));
    }

    @Test
    void serve_WhenReceiveReview() {
        when(request.is(any())).thenReturn(false);
        when(request.is(Command.REVIEW)).thenReturn(true);
        when(request.getUser_name()).thenReturn("그니");

        assertThat(botService.serve(request).getText()).contains("@channel", "리뷰", "그니");
    }

    @Test
    void serve_WhenReceiveHello() {
        when(request.is(any())).thenReturn(false);
        when(request.is(Command.HELLO)).thenReturn(true);
        when(request.getUser_name()).thenReturn("그니");

        assertThat(botService.serve(request).getText()).contains("안녕하세요", "명령", "도움", "그니");
    }
}