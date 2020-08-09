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
            return Response.ofHelp(Command.triggers());
        }

        if (request.is(Command.ROLE)) {
            return Response.ofRole(roleMemberPairsFactory.create());
        }

        if (request.is(Command.REVIEW)) {
            return Response.ofReview(request.getUser_name());
        }

        if (request.is(Command.HELLO)) {
            return Response.ofHello(request.getUser_name());
        }

        return Response.ofElse();
    }
}
