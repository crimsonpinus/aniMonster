package app.aniMonster.business.domain.social.model;

import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUpdateRequest {

//    private String social_id;

    private String nick;

    private SocialStatus status;

    private SocialIsAdult is_adult;

}
