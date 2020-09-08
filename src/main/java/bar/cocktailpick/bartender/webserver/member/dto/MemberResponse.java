package bar.cocktailpick.bartender.webserver.member.dto;

import bar.cocktailpick.bartender.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Builder
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String slackId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static List<MemberResponse> listOf(List<Member> members) {
        return members.stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }

    public static MemberResponse of(Member member) {
        return new MemberResponseBuilder()
                .id(member.getId())
                .name(member.getName())
                .slackId(member.getSlackId())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
