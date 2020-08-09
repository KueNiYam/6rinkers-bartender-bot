package bar.cocktailpick.bartender.dto;

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
        String text = "저에게 내릴 수 있는 " +
                MarkdownUtils.code("명령 목록") +
                "입니다.\n\n" +
                String.join(", ", MarkdownUtils.codes(triggers));

        return new Response(text);
    }

    public static Response ofRole(RoleMemberPairs roleMemberPairs) {
        String text = roleMemberPairs.stream()
                .map(roleMemberPair -> roleMemberPair.getRoleName() + " -> " + roleMemberPair.getMemberName())
                .collect(Collectors.joining("\n"));

        return new Response(text);
    }

    public static Response ofReview(String userName) {
        String text = String.format("<!channel> \n여러분 제발 `%s` 리뷰 좀 봐주세요. ㅠㅠ 😭", userName);

        return new Response(text);
    }

    public static Response ofHello(String userName) {
        String text = String.format("안녕하세요, `%s`님. 무엇을 도와드릴까요? 🧛‍♂️\n명령은 `도움`으로 확인할 수 있습니다.", userName);

        return new Response(text);
    }

    public static Response ofElse() {
        return new Response("아직 구현되지 않았거나 버그입니다. 그니(01074522525)로 연락주세요. ㅠㅠ 😭");
    }
}
