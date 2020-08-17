package bar.cocktailpick.bartender.webserver;

import bar.cocktailpick.bartender.webserver.dto.BotResponse;
import bar.cocktailpick.bartender.webserver.service.BotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {BotController.class})
class BotControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private BotService botService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void service() throws Exception {
        given(botService.serve(any())).willReturn(new BotResponse("서기 -> 그니"));

        mockMvc.perform(post("/api/bot")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .content("token=XXXXXXXXXXXXXXXXXX\n" +
                        "team_id=T0001\n" +
                        "team_domain=example\n" +
                        "channel_id=C2147483705\n" +
                        "channel_name=test\n" +
                        "thread_ts=1504640714.003543\n" +
                        "timestamp=1504640775.000005\n" +
                        "user_id=U2147483697\n" +
                        "user_name=Steve\n" +
                        "text=googlebot: What is the air-speed velocity of an unladen swallow?\n" +
                        "trigger_word=googlebot:"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/api/hello")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andDo(print());
    }
}