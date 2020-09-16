package bar.cocktailpick.bartender.webserver.member.dto;

import bar.cocktailpick.bartender.domain.member.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class SimpleMemberResponse {
    private final String name;

    public static SimpleMemberResponse of(Member member) {
        return new SimpleMemberResponse(member.getName());
    }

    public static List<SimpleMemberResponse> ofList(List<Member> members) {
        return members.stream()
                .map(SimpleMemberResponse::of)
                .collect(Collectors.toList());
    }
}
