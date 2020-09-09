package bar.cocktailpick.bartender.webserver.member.dto;

import bar.cocktailpick.bartender.domain.member.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequest {
    @NotBlank
    private String name;
    private String slackId;

    public Member toMember() {
        return Member.builder()
                .name(name)
                .slackId(slackId)
                .build();
    }
}
