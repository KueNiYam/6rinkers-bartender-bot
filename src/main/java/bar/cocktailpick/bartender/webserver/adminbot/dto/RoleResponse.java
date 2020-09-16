package bar.cocktailpick.bartender.webserver.adminbot.dto;

import bar.cocktailpick.bartender.domain.role.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class RoleResponse {
    private final Long id;
    private final String role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static RoleResponse of(Role role) {
        return new RoleResponse(role.getId(), role.getRole(), role.getCreatedAt(), role.getUpdatedAt());
    }

    public String toText() {
        return "아이디: " + id + " / " +
                "역할: " + role + " / " +
                "생성 날짜: " + createdAt + " / " +
                "수정 날짜: " + updatedAt;
    }
}
