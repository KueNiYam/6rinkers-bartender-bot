package bar.cocktailpick.bartender.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserProfileResponse {
    private boolean ok;
    private ProfileResponse profile;
    private String error;

    public boolean isOk() {
        return ok;
    }

    public String displayName() {
        return profile.getDisplay_name();
    }
}
