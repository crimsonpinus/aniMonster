package app.aniMonster.business.domain.social.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SocialSignRequest {

    @NotBlank
    private String social_id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String provider;
}
