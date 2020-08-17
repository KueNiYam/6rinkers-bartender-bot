package bar.cocktailpick.bartender.api.slackhook;

import bar.cocktailpick.bartender.api.slackhook.dto.HookRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackHook {
    private final static String SLACK_HOOK_URL = System.getenv("SLACK_HOOK_URL");

    private final RestTemplate restTemplate;

    public SlackHook(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void send(HookRequest hookRequest) {
        String response = restTemplate.postForObject(SLACK_HOOK_URL, hookRequest, String.class);

        validate(response);
    }

    private void validate(String response) {
        if (!"ok".equals(response)) {
            throw new RuntimeException("SlackHook 실패");
        }
    }
}
