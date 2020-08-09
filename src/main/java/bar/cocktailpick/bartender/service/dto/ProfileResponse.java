package bar.cocktailpick.bartender.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileResponse {
    private String avatar_hash;
    private String status_text;
    private String status_emoji;
    private String status_expiration;
    private String real_name;
    private String display_name;
    private String real_name_normalized;
    private String display_name_normalized;
    private String email;
    private String image_original;
    private String image_24;
    private String image_32;
    private String image_48;
    private String image_72;
    private String image_192;
    private String image_512;
    private String team;
}

