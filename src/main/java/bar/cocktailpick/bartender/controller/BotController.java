package bar.cocktailpick.bartender.controller;

import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.dto.Response;
import bar.cocktailpick.bartender.service.BotService;
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
    public ResponseEntity<Response> service(Request request) {
        if (request.isByBot()) {
            return ResponseEntity.noContent().build();
        }
        System.out.println("\n" + request + "\n");
        return ResponseEntity.ok(botService.serve(request));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("안녕하세요. 바텐더봇입니다. 🤖");
    }
}
