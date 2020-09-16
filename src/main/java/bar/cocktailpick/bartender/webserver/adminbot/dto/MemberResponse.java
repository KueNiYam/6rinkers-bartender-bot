package bar.cocktailpick.bartender.webserver.adminbot.dto;

import bar.cocktailpick.bartender.domain.member.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String slackId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(),
                member.getName(),
                member.getSlackId(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    public String toText() {
        return "아이디: " + id + " / " +
                "이름: " + name + " / " +
                "슬랙 아이디: " + slackId + " / " +
                "생성 날짜: " + createdAt + " / " +
                "수정 날짜: " + updatedAt;
    }
}
