package app.aniMonster.postgresql.db.social.profile.entity;

import app.aniMonster.postgresql.db.social.profile.enums.SocialProfileGender;
import app.aniMonster.postgresql.db.social.profile.enums.SocialProfilePermit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tbl_social_profile")
public class SocialProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_social_profile_id_gen")
    @SequenceGenerator(name = "tbl_social_profile_id_gen", sequenceName = "tbl_social_profile_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "social_id", nullable = false, length = 150)
    private String socialId;

    @Size(max = 20)
    @Column(name = "gender", length = 20)
    @Enumerated(EnumType.STRING)
    private SocialProfileGender gender;

    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "birth_year")
    private LocalDate birthYear;

    @Size(max = 5)
    @Column(name = "birth", length = 5)
    private String birth;

    @Size(max = 5)
    @Column(name = "service_terms", length = 5)
    @Enumerated(EnumType.STRING)
    private SocialProfilePermit serviceTerms;

    @Size(max = 5)
    @Column(name = "privacy_terms", length = 5)
    @Enumerated(EnumType.STRING)
    private SocialProfilePermit privacyTerms;

    @Size(max = 5)
    @Column(name = "marketing_opt_in_terms", length = 5)
    @Enumerated(EnumType.STRING)
    private SocialProfilePermit marketingOptInTerms;

    @Size(max = 5)
    @Column(name = "third_party_terms", length = 5)
    @Enumerated(EnumType.STRING)
    private SocialProfilePermit thirdPartyTerms;

}