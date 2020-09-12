package bar.cocktailpick.bartender.webserver.bot.service;

import bar.cocktailpick.bartender.api.slackapi.SlackApi;
import bar.cocktailpick.bartender.api.slackapi.dto.UserProfileResponse;
import bar.cocktailpick.bartender.domain.MemberFactory;
import bar.cocktailpick.bartender.domain.RoleMembersFactory;
import bar.cocktailpick.bartender.webserver.bot.dto.BotRequest;
import bar.cocktailpick.bartender.webserver.bot.dto.BotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BotService {
    private final RoleMembersFactory roleMembersFactory;
    private final MemberFactory memberFactory;
    private final SlackApi slackApi;

    public BotResponse serve(BotRequest botRequest) {
        return Command.find(botRequest)
                .behavior.apply(this, botRequest);
    }

    private BotResponse help(BotRequest botRequest) {
        return BotResponse.ofHelp(Command.sortedTriggers());
    }

    private BotResponse role(BotRequest botRequest) {
        return BotResponse.ofRole(roleMembersFactory.shuffled());
    }

    private BotResponse review(BotRequest botRequest) {
        UserProfileResponse userProfileResponse = slackApi.getProfile(botRequest.getUser_id());

        if (userProfileResponse.isOk()) {
            return BotResponse.ofReview(userProfileResponse.displayName());
        }

        return BotResponse.displayNameNotFound();
    }

    private BotResponse draw(BotRequest botRequest) {
        return BotResponse.ofDraw(memberFactory.random());
    }

    private BotResponse hello(BotRequest botRequest) {
        UserProfileResponse userProfileResponse = slackApi.getProfile(botRequest.getUser_id());

        if (userProfileResponse.isOk()) {
            return BotResponse.ofHello(userProfileResponse.displayName());
        }

        return BotResponse.displayNameNotFound();
    }

    public enum Command {
        HELP("도움", BotService::help),
        ROLE("역할", BotService::role),
        REVIEW("리뷰", BotService::review),
        HELLO("안녕", BotService::hello),
        DRAW("뽑기", BotService::draw);

        private final String trigger;
        private final BiFunction<BotService, BotRequest, BotResponse> behavior;

        Command(String trigger, BiFunction<BotService, BotRequest, BotResponse> behavior) {
            this.trigger = trigger;
            this.behavior = behavior;
        }

        public static List<String> sortedTriggers() {
            return Arrays.stream(values())
                    .map(command -> command.trigger)
                    .sorted()
                    .collect(Collectors.toList());
        }

        public static Command find(BotRequest botRequest) {
            return Arrays.stream(values())
                    .filter(command -> botRequest.isByTrigger(command.trigger))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("처리할 수 있는 요청이 아닙니다."));
        }
    }
}
