package bar.cocktailpick.bartender.webserver;

import bar.cocktailpick.bartender.webserver.dto.BotRequest;
import bar.cocktailpick.bartender.webserver.dto.BotResponse;
import bar.cocktailpick.bartender.webserver.service.BotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/api")
public class BotController {
    private final BotService botService;

    @PostMapping("/bot")
    public ResponseEntity<BotResponse> service(BotRequest botRequest) {
        if (botRequest.isByBot()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(botService.serve(botRequest));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("안녕하세요. 바텐더봇입니다. 🤖");
    }
}
