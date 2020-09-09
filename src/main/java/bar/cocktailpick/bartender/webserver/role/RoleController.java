package bar.cocktailpick.bartender.webserver.role;

import bar.cocktailpick.bartender.webserver.role.dto.RoleRequest;
import bar.cocktailpick.bartender.webserver.role.dto.RoleResponse;
import bar.cocktailpick.bartender.webserver.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid RoleRequest roleRequest) {
        Long createdId = roleService.add(roleRequest);

        return ResponseEntity.created(URI.create("/api/roles/" + createdId)).build();
    }
}
