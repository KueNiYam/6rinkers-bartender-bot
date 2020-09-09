package bar.cocktailpick.bartender.webserver.role.service;

import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.domain.role.RoleRepository;
import bar.cocktailpick.bartender.webserver.role.dto.RoleRequest;
import bar.cocktailpick.bartender.webserver.role.dto.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleResponse> findAll() {
        List<Role> roles = roleRepository.findAll();

        return RoleResponse.listOf(roles);
    }

    public Long add(RoleRequest roleRequest) {
        return roleRepository.save(roleRequest.toRole()).getId();
    }
}
