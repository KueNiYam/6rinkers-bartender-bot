package bar.cocktailpick.bartender.api.slackhook;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackHook {
    private final static String URL = "https://hooks.slack.com/services/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";
    private final RestTemplate restTemplate;

    public SlackHook(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
}
