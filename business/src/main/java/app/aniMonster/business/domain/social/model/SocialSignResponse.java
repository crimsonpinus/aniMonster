package app.aniMonster.business.domain.social.model;

import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialSignResponse {

    private String socialId;

    private SocialIsAdult isAdult;

    private SocialStatus status;
}
