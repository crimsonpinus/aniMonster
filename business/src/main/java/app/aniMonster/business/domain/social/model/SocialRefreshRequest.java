package app.aniMonster.business.domain.social.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialRefreshRequest {

    @NotBlank
    private String refresh_token;
}
