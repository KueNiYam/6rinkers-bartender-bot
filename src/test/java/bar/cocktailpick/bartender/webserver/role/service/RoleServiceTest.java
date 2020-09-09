package bar.cocktailpick.bartender.webserver.role.service;

import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.domain.role.RoleRepository;
import bar.cocktailpick.bartender.webserver.role.dto.RoleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleService = new RoleService(roleRepository);
    }

    @Test
    void findAll() {
        Role role1 = Role.builder()
                .id(1L)
                .role("취사병")
                .emoji("😀")
                .build();

        Role role2 = Role.builder()
                .id(2L)
                .role("서기")
                .emoji("🤣")
                .build();

        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));

        assertThat(roleService.findAll()).hasSize(2);
    }

    @Test
    void add() {
        Role role = Role.builder()
                .id(1L)
                .role("취사병")
                .emoji("😀")
                .build();

        when(roleRepository.save(any())).thenReturn(role);

        RoleRequest roleRequest = new RoleRequest("취사병", "😀");

        assertThat(roleService.add(roleRequest)).isEqualTo(1L);
    }
}