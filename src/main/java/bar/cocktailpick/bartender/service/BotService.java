package bar.cocktailpick.bartender.service;

import bar.cocktailpick.bartender.domain.MemberFactory;
import bar.cocktailpick.bartender.domain.RoleMemberPairsFactory;
import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.dto.Response;
import bar.cocktailpick.bartender.service.api.SlackApi;
import bar.cocktailpick.bartender.service.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BotService {
    private final RoleMemberPairsFactory roleMemberPairsFactory;
    private final MemberFactory memberFactory;
    private final SlackApi slackApi;

    public Response serve(Request request) {
        return Command.find(request)
                .behavior.apply(this, request);
    }

    private Response help(Request request) {
        return Response.ofHelp(Command.sortedTriggers());
    }

    private Response role(Request request) {
        return Response.ofRole(roleMemberPairsFactory.shuffle());
    }

    private Response review(Request request) {
        UserProfileResponse userProfileResponse = slackApi.getProfile(request.getUser_id());

        if (userProfileResponse.isOk()) {
            return Response.ofReview(userProfileResponse.displayName());
        }

        return Response.displayNameNotFound();
    }

    private Response draw(Request request) {
        return Response.ofDraw(memberFactory.random());
    }

    private Response hello(Request request) {
        UserProfileResponse userProfileResponse = slackApi.getProfile(request.getUser_id());

        if (userProfileResponse.isOk()) {
            return Response.ofHello(userProfileResponse.displayName());
        }

        return Response.displayNameNotFound();
    }

    public enum Command {
        HELP("도움", BotService::help),
        ROLE("역할", BotService::role),
        REVIEW("리뷰", BotService::review),
        HELLO("안녕", BotService::hello),
        DRAW("뽑기", BotService::draw);

        private final String trigger;
        private final BiFunction<BotService, Request, Response> behavior;

        Command(String trigger, BiFunction<BotService, Request, Response> behavior) {
            this.trigger = trigger;
            this.behavior = behavior;
        }

        public static List<String> sortedTriggers() {
            return Arrays.stream(values())
                    .map(command -> command.trigger)
                    .sorted()
                    .collect(Collectors.toList());
        }

        public static Command find(Request request) {
            return Arrays.stream(values())
                    .filter(command -> request.isByTrigger(command.trigger))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("처리할 수 있는 요청이 아닙니다."));
        }
    }
}
