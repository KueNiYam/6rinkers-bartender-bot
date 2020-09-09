package bar.cocktailpick.bartender.webserver.role.dto;

import bar.cocktailpick.bartender.domain.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@RequiredArgsConstructor
public class RoleResponse {
    private final Long id;
    private final String role;
    private final String emoji;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static List<RoleResponse> listOf(List<Role> roles) {
        return roles.stream()
                .map(RoleResponse::of)
                .collect(Collectors.toList());
    }

    public static RoleResponse of(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getRole())
                .emoji(role.getEmoji())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
}
