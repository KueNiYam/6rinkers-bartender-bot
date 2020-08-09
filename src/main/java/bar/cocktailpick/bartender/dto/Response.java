package bar.cocktailpick.bartender.dto;

import bar.cocktailpick.bartender.domain.Member;
import bar.cocktailpick.bartender.domain.Role;
import bar.cocktailpick.bartender.domain.RoleMemberPair;
import bar.cocktailpick.bartender.domain.RoleMemberPairs;
import bar.cocktailpick.bartender.util.MarkdownUtils;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Response {
    private final String text;

    public static Response ofHelp(List<String> triggers) {
        String message = "저에게 내릴 수 있는 " +
                MarkdownUtils.code("명령 목록") +
                "입니다.\n\n";

        String list = String.join(", ", MarkdownUtils.codes(triggers));

        return new Response(message + MarkdownUtils.blockQuote(list));
    }

    public static Response ofRole(RoleMemberPairs roleMemberPairs) {
        String roles = roleMemberPairs.stream()
                .map(roleMemberPair -> roleMemberPair.getRoleName() + " -> " + roleMemberPair.getMemberName())
                .collect(Collectors.joining("\n"));

        RoleMemberPair masterMember = roleMemberPairs.find(Role.MASTER);
        RoleMemberPair leaderMember = roleMemberPairs.find(Role.LEADER);

        String message = String.format("%s %s은(는) 데일리 회의록을, %s %s은(는) 회의록을 작성해주세요.",
                MarkdownUtils.bold(masterMember.getRoleName()), MarkdownUtils.code(masterMember.getMemberName()),
                MarkdownUtils.bold(leaderMember.getRoleName()), MarkdownUtils.code(leaderMember.getMemberName()));

        String text = String.format("%s\n\n%s", roles, MarkdownUtils.blockQuote(message));

        return new Response(text);
    }

    public static Response ofReview(String userName) {
        String message = MarkdownUtils.blockQuote(String.format("%s가 %s을 보냈습니다. \uD83D\uDE80",
                MarkdownUtils.code(userName), MarkdownUtils.bold("리뷰 요청")));

        return new Response(MarkdownUtils.toChannel() + "\n" + message);
    }

    public static Response ofDraw(Member random) {
        String text = String.format("축하합니다. %s님께서 당첨되셨습니다. \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89",
                MarkdownUtils.bold(random.getMemberName()));
        return new Response(text);
    }

    public static Response ofHello(String userName) {
        String text = String.format("안녕하세요, %s님. 무엇을 도와드릴까요? 🧛‍♂️\n명령은 `도움`으로 확인할 수 있습니다.",
                MarkdownUtils.code(userName));

        return new Response(text);
    }

    public static Response ofElse() {
        return new Response("아직 구현되지 않았거나 버그입니다. 그니(01074522525)로 연락주세요. ㅠㅠ 😭");
    }
}
