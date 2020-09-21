package bar.cocktailpick.bartender.domain.rolemembers.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleMemberCountDto {
    private final String role;
    private final String member;
    private final Long count;
}
