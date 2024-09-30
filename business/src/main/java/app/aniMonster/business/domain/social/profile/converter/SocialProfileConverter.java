package app.aniMonster.business.domain.social.profile.converter;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.social.profile.model.SocialProfileRequest;
import app.aniMonster.business.domain.social.profile.model.SocialProfileResponse;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.postgresql.db.social.profile.entity.SocialProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SocialProfileConverter {
    @Autowired
    DbEncryptUtil encryptUtil;

    public SocialProfileEntity toEntity(SocialProfileRequest socialProfileRequest) {
        return Optional.ofNullable(socialProfileRequest)
                .map(it -> {
                    return SocialProfileEntity.builder()
                            .socialId(encryptUtil.encryptEncode(it.getSocial_id()))
                            .gender(it.getGender())
                            .phone(it.getPhone())
                            .birthYear(it.getBirth_year())
                            .birth(it.getBirth())
                            .serviceTerms(it.getService_terms())
                            .privacyTerms(it.getPrivacy_terms())
                            .marketingOptInTerms(it.getMarketing_opt_in_terms())
                            .thirdPartyTerms(it.getThird_party_terms())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social profile could not be converted to entity"));
    }

    public SocialProfileEntity toEntity(String socialId) {
        return Optional.ofNullable(socialId)
                .map(it -> {
                    return SocialProfileEntity.builder()
                            .socialId(encryptUtil.encryptEncode(socialId))
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Id Null"));
    }

    public SocialProfileResponse toResponse(SocialProfileEntity socialProfileEntity) {
        return Optional.ofNullable(socialProfileEntity)
                .map(it -> {
                    return SocialProfileResponse.builder()
                            .social_id(encryptUtil.encryptDecode(it.getSocialId()))
                            .gender(it.getGender())
                            .phone(it.getPhone())
                            .birth_year(it.getBirthYear())
                            .birth(it.getBirth())
                            .service_terms(it.getServiceTerms())
                            .privacy_terms(it.getPrivacyTerms())
                            .marketing_opt_in_terms(it.getMarketingOptInTerms())
                            .third_party_terms(it.getThirdPartyTerms())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST,"Social profile could not be converted to response"));
    }
}
