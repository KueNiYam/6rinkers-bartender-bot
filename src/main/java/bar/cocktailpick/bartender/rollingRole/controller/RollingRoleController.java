package bar.cocktailpick.bartender.rollingRole.controller;

import bar.cocktailpick.bartender.rollingRole.dto.RollingRoleResponse;
import bar.cocktailpick.bartender.rollingRole.service.RollingRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/rolling-role")
public class RollingRoleController {
    private final RollingRoleService rollingRoleService;

    @GetMapping
    public ResponseEntity<RollingRoleResponse> rollingRole() {
        return ResponseEntity.ok(rollingRoleService.rollingRole());
    }

    @PostMapping
    public ResponseEntity<RollingRoleResponse> rollingRoleToSlack() {
        return ResponseEntity.ok(rollingRoleService.rollingRole());
    }
}
