package bar.cocktailpick.bartender.webserver.rolemembers.dto;

import bar.cocktailpick.bartender.domain.rolemembers.RoleMembers;
import bar.cocktailpick.bartender.util.MarkdownUtils;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RoleMembersResponse {
    private final List<RoleMemberResponse> roleMemberResponse;
    private final LocalDateTime createdAt;

    public static RoleMembersResponse of(RoleMembers roleMembers) {
        List<RoleMemberResponse> roleMemberResponses = roleMembers.getRoleMembers().stream()
                .map(RoleMemberResponse::of)
                .collect(Collectors.toList());

        return new RoleMembersResponse(roleMemberResponses, roleMembers.getCreatedAt());
    }

    public String toText() {
        return roleMemberResponse.stream()
                .map(RoleMemberResponse::toText)
                .collect(Collectors.joining(System.lineSeparator())) +
                System.lineSeparator() + System.lineSeparator() +
                MarkdownUtils.italic(localDateTimeToLocalDateString());
    }

    private String localDateTimeToLocalDateString() {
        if (createdAt == null) {
            return "날짜를 불러올 수 없습니다.";
        }

        return createdAt.toLocalDate().toString();
    }

    public boolean isEmpty() {
        return roleMemberResponse.isEmpty();
    }
}
