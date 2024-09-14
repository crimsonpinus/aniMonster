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

    private String socialId;

    private SocialProfileGender gender;

    private String phone;

    private LocalDate birthYear;

    private String birth;

    private SocialProfilePermit serviceTerms;

    private SocialProfilePermit privacyTerms;

    private SocialProfilePermit marketingOptInTerms;

    private SocialProfilePermit thirdPartyTerms;
}
