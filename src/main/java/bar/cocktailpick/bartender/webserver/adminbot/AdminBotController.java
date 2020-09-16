package bar.cocktailpick.bartender.webserver.adminbot;

import bar.cocktailpick.bartender.webserver.adminbot.dto.AdminBotResponse;
import bar.cocktailpick.bartender.webserver.adminbot.service.AdminBotService;
import bar.cocktailpick.bartender.webserver.common.dto.BotRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin-bot")
public class AdminBotController {
    private final AdminBotService adminBotService;

    @PostMapping
    public ResponseEntity<AdminBotResponse> service(BotRequest botRequest) {
        if (botRequest.isByBot()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adminBotService.serve(botRequest));
    }
}
