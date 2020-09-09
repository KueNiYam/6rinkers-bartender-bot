package bar.cocktailpick.bartender.webserver.role;

import bar.cocktailpick.bartender.webserver.role.dto.RoleResponse;
import bar.cocktailpick.bartender.webserver.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll() {
        List<RoleResponse> roles = roleService.findAll();

        return ResponseEntity.ok(roles);
    }
}
