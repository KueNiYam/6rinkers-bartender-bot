package bar.cocktailpick.bartender.webserver.bot.dto;

import bar.cocktailpick.bartender.util.MarkdownUtils;
import bar.cocktailpick.bartender.webserver.member.dto.SimpleMemberResponse;
import bar.cocktailpick.bartender.webserver.rolemembers.dto.RoleMembersResponse;
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

    public static BotResponse ofCreateRole(RoleMembersResponse roleMembersResponse) {
        String head = "새로운 역할이 생성되었습니다." + System.lineSeparator() + System.lineSeparator();

        return new BotResponse(head + roleMembersResponse.toText());
    }

    public static BotResponse ofRoleMembersNoContent() {
        return new BotResponse("현재 역할이 없습니다." +
                System.lineSeparator() +
                "그니에게 문의하거나 바텐더 관리자 봇에서 추가해주세요.");
    }

    public static BotResponse ofCurrentRole(RoleMembersResponse currentRole) {
        return new BotResponse(currentRole.toText());
    }

    public static BotResponse ofReview(String userName) {
        String message = MarkdownUtils.blockQuote(String.format("%s가 %s을 보냈습니다. \uD83D\uDE80",
                MarkdownUtils.bold(userName), MarkdownUtils.bold("리뷰 요청")));

        return new BotResponse(MarkdownUtils.toChannel() + "\n" + message);
    }

    public static BotResponse ofDrawOne(SimpleMemberResponse member) {
        String text = String.format("축하합니다. %s님께서 당첨되셨습니다. \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89",
                MarkdownUtils.bold(member.getName()));

        return new BotResponse(text);
    }

    public static BotResponse ofDrawTwo(List<SimpleMemberResponse> members) {
        String boldNames = members.stream()
                .map(member -> MarkdownUtils.bold(member.getName()))
                .collect(Collectors.joining(", "));

        String text = "축하합니다. " + boldNames + "님들께서 당첨되셨습니다. \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89";

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

    public static BotResponse ofError(RuntimeException exception) {
        return new BotResponse("예외가 발생했습니다." + System.lineSeparator() +
                MarkdownUtils.code(exception.getMessage()));
    }

    public static BotResponse ofPatchNote() {
        return new BotResponse(MarkdownUtils.bold("패치 노트") +
                System.lineSeparator() +
                "https://github.com/KueNiYam/6rinkers-bartender-bot");
    }
}
