package bar.cocktailpick.bartender.webserver.bot.service;

import bar.cocktailpick.bartender.api.slackapi.SlackApi;
import bar.cocktailpick.bartender.api.slackapi.dto.UserProfileResponse;
import bar.cocktailpick.bartender.domain.MemberFactory2;
import bar.cocktailpick.bartender.domain.RoleMembersFactory2;
import bar.cocktailpick.bartender.webserver.bot.dto.BotResponse;
import bar.cocktailpick.bartender.webserver.common.dto.BotRequest;
import bar.cocktailpick.bartender.webserver.rolemembers.dto.RoleMembersResponse;
import bar.cocktailpick.bartender.webserver.rolemembers.service.RoleMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BotService {
    private final RoleMembersFactory2 roleMembersFactory;
    private final MemberFactory2 memberFactory;
    private final SlackApi slackApi;
    private final RoleMembersService roleMembersService;

    public BotResponse serve(BotRequest botRequest) {
        return Command.find(botRequest)
                .behavior.apply(this, botRequest);
    }

    private BotResponse help(BotRequest botRequest) {
        return BotResponse.ofHelp(Command.sortedTriggers());
    }

    private BotResponse createRole(BotRequest botRequest) {
        try {
            RoleMembersResponse roleMembersResponse = roleMembersService.create();

            return createBotResponseOfCreateRole(roleMembersResponse);
        } catch (RuntimeException e) {
            return BotResponse.ofError(e);
        }
    }

    private BotResponse currentRole(BotRequest botRequest) {
        return BotResponse.ofCurrentRole(roleMembersService.current());
    }

    private BotResponse createBotResponseOfCreateRole(RoleMembersResponse roleMembersResponse) {
        if (roleMembersResponse.isEmpty()) {
            return BotResponse.ofRoleMembersNoContent();
        }

        return BotResponse.ofCreateRole(roleMembersResponse);
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
        CREATE_ROLE("새로운 역할", BotService::createRole),
        LAST_ROLE("현재 역할", BotService::currentRole),
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
