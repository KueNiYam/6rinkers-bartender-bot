package bar.cocktailpick.bartender.webserver.dto;

import bar.cocktailpick.bartender.domain.Member;
import bar.cocktailpick.bartender.domain.Role;
import bar.cocktailpick.bartender.domain.RoleMember;
import bar.cocktailpick.bartender.domain.RoleMembers;
import bar.cocktailpick.bartender.util.MarkdownUtils;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class BotResponse {
    private final String text;

    public static BotResponse ofHelp(List<String> triggers) {
        String message = "저에게 내릴 수 있는 " +
                MarkdownUtils.code("명령 목록") +
                "입니다.\n\n";

        String list = String.join(", ", MarkdownUtils.codes(triggers));

        return new BotResponse(message + MarkdownUtils.blockQuote(list));
    }

    public static BotResponse ofRole(RoleMembers roleMembers) {
        String roles = roleMembers.stream()
                .map(roleMember -> roleMember.getRoleName() + " -> " + roleMember.getMemberName())
                .collect(Collectors.joining("\n"));

        RoleMember masterMember = roleMembers.find(Role.MASTER);
        RoleMember leaderMember = roleMembers.find(Role.LEADER);

        String message = String.format("%s %s은(는) 데일리 회의록을, %s %s은(는) 회의록을 작성해주세요.",
                MarkdownUtils.bold(masterMember.getRoleName()), MarkdownUtils.code(masterMember.getMemberName()),
                MarkdownUtils.bold(leaderMember.getRoleName()), MarkdownUtils.code(leaderMember.getMemberName()));

        String text = String.format("%s\n\n%s", roles, MarkdownUtils.blockQuote(message));

        return new BotResponse(text);
    }

    public static BotResponse ofReview(String userName) {
        String message = MarkdownUtils.blockQuote(String.format("%s가 %s을 보냈습니다. \uD83D\uDE80",
                MarkdownUtils.bold(userName), MarkdownUtils.bold("리뷰 요청")));

        return new BotResponse(MarkdownUtils.toChannel() + "\n" + message);
    }

    public static BotResponse ofDraw(Member random) {
        String text = String.format("축하합니다. %s님께서 당첨되셨습니다. \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89",
                MarkdownUtils.bold(random.getMemberName()));
        return new BotResponse(text);
    }

    public static BotResponse ofHello(String userName) {
        String text = String.format("안녕하세요, %s님. 무엇을 도와드릴까요? 🧛‍♂️\n명령은 `도움`으로 확인할 수 있습니다.",
                MarkdownUtils.bold(userName));

        return new BotResponse(text);
    }

    public static BotResponse displayNameNotFound() {
        return new BotResponse("slackApi에서 displayName을 찾을 수 없습니다.");
    }

    public static BotResponse ofElse() {
        return new BotResponse("아직 구현되지 않았거나 버그입니다. 그니(01074522525)로 연락주세요. ㅠㅠ 😭");
    }
}
