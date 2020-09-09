package bar.cocktailpick.bartender.webserver.role.dto;

import bar.cocktailpick.bartender.domain.role.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleRequest {
    @NotBlank
    private String role;
    @NotBlank
    private String emoji;

    public Role toRole() {
        return Role.builder()
                .role(role)
                .emoji(emoji)
                .build();
    }
}
