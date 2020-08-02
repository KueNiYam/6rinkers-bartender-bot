package bar.cocktailpick.bartender.rollingRole.service;

import bar.cocktailpick.bartender.rollingRole.domain.RoleMemberPairs;
import bar.cocktailpick.bartender.rollingRole.domain.RoleMemberPairsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RollingRoleServiceTest {
    private RollingRoleService rollingRoleService;

    @Mock
    private RoleMemberPairsFactory roleMemberPairsFactory;

    @Mock
    private RoleMemberPairs roleMemberPairs;

    @BeforeEach
    void setUp() {
        rollingRoleService = new RollingRoleService(roleMemberPairsFactory);
    }

    @Test
    void rollingRole() {
        when(roleMemberPairsFactory.create()).thenReturn(roleMemberPairs);
        when(roleMemberPairs.text()).thenReturn("그니 -> 서기");

        assertThat(rollingRoleService.rollingRole()
                .getText())
                .isEqualTo("그니 -> 서기");
    }
}