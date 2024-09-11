package app.aniMonster.business.domain.social.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialResponse {

    private String socialId;

    private String name;

    private String email;

    private String isAdult;
}
