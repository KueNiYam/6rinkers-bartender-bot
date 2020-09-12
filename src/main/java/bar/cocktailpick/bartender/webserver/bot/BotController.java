package bar.cocktailpick.bartender.webserver.bot;

import bar.cocktailpick.bartender.webserver.bot.dto.BotRequest;
import bar.cocktailpick.bartender.webserver.bot.dto.BotResponse;
import bar.cocktailpick.bartender.webserver.bot.service.BotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/api/bot")
public class BotController {
    private final BotService botService;

    @PostMapping
    public ResponseEntity<BotResponse> service(BotRequest botRequest) {
        if (botRequest.isByBot()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(botService.serve(botRequest));
    }
}
