package bar.cocktailpick.bartender.webserver.service;

import bar.cocktailpick.bartender.api.slackapi.SlackApi;
import bar.cocktailpick.bartender.api.slackapi.dto.UserProfileResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlackApiTest {
    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserProfileResponse userProfileResponse;

    @Test
    void getEnv() {
        assertThat(SlackApi.SLACK_API_TOKEN).isNotNull();
    }

    @Test
    void getProfile() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(restTemplate.getForObject(anyString(), any(), anyString(), anyString())).thenReturn(userProfileResponse);

        SlackApi slackApi = new SlackApi(restTemplateBuilder);

        assertThat(slackApi.getProfile("abc")).isNotNull();
    }
}