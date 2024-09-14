package app.aniMonster.business.domain.social.profile.model;

import app.aniMonster.postgresql.db.social.profile.enums.SocialProfileGender;
import app.aniMonster.postgresql.db.social.profile.enums.SocialProfilePermit;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfileRequest {

    @NotBlank
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
