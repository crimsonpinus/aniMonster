package app.aniMonster.business.domain.social.profile.model;

import app.aniMonster.postgresql.db.social.profile.enums.SocialProfileGender;
import app.aniMonster.postgresql.db.social.profile.enums.SocialProfilePermit;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfileResponse {

    private String social_id;

    private SocialProfileGender gender;

    private String phone;

    private LocalDate birth_year;

    private String birth;

    private SocialProfilePermit service_terms;

    private SocialProfilePermit privacy_terms;

    private SocialProfilePermit marketing_opt_in_terms;

    private SocialProfilePermit third_party_terms;
}
