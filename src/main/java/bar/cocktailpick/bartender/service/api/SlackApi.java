package bar.cocktailpick.bartender.service.api;

import bar.cocktailpick.bartender.service.dto.UserProfileResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackApi {
    public static final String SLACK_API_TOKEN = System.getenv("SLACK_BOT_TOKEN");

    private final RestTemplate restTemplate;

    public SlackApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public UserProfileResponse getProfile(String userId) {
        return restTemplate.getForObject("https://slack.com/api/users.profile.get" +
                "?token={token}" +
                "&user={userId}" +
                "&pretty=1", UserProfileResponse.class, SLACK_API_TOKEN, userId);
    }
}
