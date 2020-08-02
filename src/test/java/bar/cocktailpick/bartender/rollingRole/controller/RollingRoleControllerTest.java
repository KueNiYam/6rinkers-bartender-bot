package bar.cocktailpick.bartender.rollingRole.controller;

import bar.cocktailpick.bartender.rollingRole.dto.RollingRoleResponse;
import bar.cocktailpick.bartender.rollingRole.service.RollingRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RollingRoleController.class})
class RollingRoleControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private RollingRoleService rollingRoleService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void rollingRole() throws Exception {
        given(rollingRoleService.rollingRole()).willReturn(new RollingRoleResponse("서기 -> 그니"));

        mockMvc.perform(get("/rolling-role")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}