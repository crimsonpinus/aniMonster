package app.aniMonster.business.domain.social.model;

import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialResponse {

    private String social_id;

    private String name;

    private String email;

    private String nick;

    private String provider;

    private SocialStatus status;

    private SocialIsAdult is_adult;

    private Instant registered_at;

}
