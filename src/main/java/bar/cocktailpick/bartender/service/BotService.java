package bar.cocktailpick.bartender.service;

import bar.cocktailpick.bartender.domain.RoleMemberPairsFactory;
import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.dto.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class BotService {
    private final RoleMemberPairsFactory roleMemberPairsFactory;

    public Response serve(Request request) {
        if (request.is(Command.HELP)) {
            return new Response(Command.commands());
        }

        if (request.is(Command.ROLE)) {
            return Response.of(roleMemberPairsFactory.create());
        }

        if (request.is(Command.REVIEW)) {
            return new Response(String.format("@channel\n제발 %s 리뷰 좀 봐주세요. ㅠㅠ", request.getUser_name()));
        }

        if (request.is(Command.HELLO)) {
            return new Response(String.format("안녕하세요, %s님. 무엇을 도와드릴까요?\n명령은 \"도움\"으로 확인할 수 있습니다.", request.getUser_name()));
        }

        return new Response("아직 구현되지 않았어요. ㅠㅠ");
    }
}
