package bar.cocktailpick.bartender.service;

import bar.cocktailpick.bartender.domain.CustomDate;
import bar.cocktailpick.bartender.domain.RoleMemberPairs;
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
            return new Response("저에게 내릴 수 있는 `명령 목록`입니다.\n\n" + Command.commands());
        }

        if (request.is(Command.ROLE)) {
            CustomDate customDate = CustomDate.of(request.getTimestamp());
            RoleMemberPairs roleMemberPairs = roleMemberPairsFactory.create();
            return new Response(customDate.text() + "일 `역할`입니다.\n\n" + roleMemberPairs.text());
        }

        if (request.is(Command.REVIEW)) {
            return new Response(String.format("<!channel> \n여러분 제발 `%s` 리뷰 좀 봐주세요. ㅠㅠ 😭", request.getUser_name()));
        }

        if (request.is(Command.HELLO)) {
            return new Response(String.format("안녕하세요, `%s`님. 무엇을 도와드릴까요? 🧛‍♂️\n명령은 `도움`으로 확인할 수 있습니다.", request.getUser_name()));
        }

        return new Response("아직 구현되지 않았거나 버그입니다. 그니(01074522525)로 연락주세요. ㅠㅠ 😭");
    }
}
