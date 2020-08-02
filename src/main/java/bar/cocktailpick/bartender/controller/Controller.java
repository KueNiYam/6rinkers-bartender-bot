package bar.cocktailpick.bartender.controller;

import bar.cocktailpick.bartender.dto.RollingRoleResponse;
import bar.cocktailpick.bartender.service.RollingRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/rolling-role")
public class Controller {
    private final RollingRoleService rollingRoleService;

    @PostMapping
    public ResponseEntity<RollingRoleResponse> rollingRole() {
        return ResponseEntity.ok(rollingRoleService.rollingRole());
    }
}
