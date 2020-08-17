package bar.cocktailpick.bartender.api.slackhook;

import bar.cocktailpick.bartender.api.slackhook.dto.HookRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlackHookTest {
    private SlackHook slackHook;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        slackHook = new SlackHook(restTemplateBuilder);
    }

    @Test
    void send() {
        HookRequest hookRequest = new HookRequest(Collections.emptyList());

        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn("ok");

        slackHook.send(hookRequest);
    }

    @DisplayName("Hook의 리턴 값이 ok가 아닐 경우 예외를 던진다.")
    @Test
    void send_ThrowExceptionWhenNotReturnOk() {
        HookRequest hookRequest = new HookRequest(Collections.emptyList());

        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn("invalid");

        assertThatThrownBy(() -> slackHook.send(hookRequest))
                .isInstanceOf(RuntimeException.class);
    }
}