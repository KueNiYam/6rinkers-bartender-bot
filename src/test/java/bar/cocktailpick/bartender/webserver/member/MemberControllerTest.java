package bar.cocktailpick.bartender.webserver.member;

import bar.cocktailpick.bartender.webserver.member.dto.MemberRequest;
import bar.cocktailpick.bartender.webserver.member.dto.MemberResponse;
import bar.cocktailpick.bartender.webserver.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void findAll() throws Exception {
        List<MemberResponse> memberResponses = Arrays.asList(
                new MemberResponse(0L, "그니", "abcdefgh", LocalDateTime.now(), LocalDateTime.now()),
                new MemberResponse(1L, "토니", "hgfedcba", LocalDateTime.now(), LocalDateTime.now())
        );

        given(memberService.findAll()).willReturn(memberResponses);

        mockMvc.perform(get("/api/members")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void add() throws Exception {
        MemberRequest memberRequest = new MemberRequest("그니", null);

        given(memberService.add(any())).willReturn(1L);

        mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string(LOCATION, "/api/members/1"))
                .andDo(print());
    }
}