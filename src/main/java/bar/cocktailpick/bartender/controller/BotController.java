package bar.cocktailpick.bartender.controller;

import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.dto.Response;
import bar.cocktailpick.bartender.service.BotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
public class BotController {
    private final BotService botService;

    @PostMapping("/service")
    public ResponseEntity<Response> service(Request request) {
        return ResponseEntity.ok(botService.serve(request));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("안녕하세요. 바텐더봇입니다. 🤖");
    }
}
