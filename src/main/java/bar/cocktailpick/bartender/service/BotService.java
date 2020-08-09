package bar.cocktailpick.bartender.service;

import bar.cocktailpick.bartender.domain.RoleMemberPairsFactory;
import bar.cocktailpick.bartender.dto.Request;
import bar.cocktailpick.bartender.dto.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class BotService {
    private final RoleMemberPairsFactory roleMemberPairsFactory;

    public Response serve(Request request) {
        return Command.find(request)
                .behavior.apply(this, request);
    }

    private Response help(Request request) {
        return Response.ofHelp(Command.triggers());
    }

    private Response role(Request request) {
        return Response.ofRole(roleMemberPairsFactory.create());
    }

    private Response review(Request request) {
        return Response.ofReview(request.getUser_name());
    }

    private Response hello(Request request) {
        return Response.ofHello(request.getUser_name());
    }

    public enum Command {
        HELP("도움", BotService::help),
        ROLE("역할", BotService::role),
        REVIEW("리뷰", BotService::review),
        HELLO("안녕", BotService::hello);

        private final String trigger;
        private final BiFunction<BotService, Request, Response> behavior;

        Command(String trigger, BiFunction<BotService, Request, Response> behavior) {
            this.trigger = trigger;
            this.behavior = behavior;
        }

        public static List<String> triggers() {
            return Arrays.stream(values())
                    .map(command -> command.trigger)
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
