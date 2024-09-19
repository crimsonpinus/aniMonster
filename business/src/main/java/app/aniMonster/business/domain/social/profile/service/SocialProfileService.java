package app.aniMonster.business.domain.social.profile.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.social.profile.entity.SocialProfileEntity;
import app.aniMonster.postgresql.db.social.profile.repository.SocialProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialProfileService {

    private final SocialProfileRepository socialProfileRepository;

    @Transactional
    public SocialProfileEntity update(SocialProfileEntity socialProfileEntity) {
        var socialProfileCheck = socialProfileRepository.findFirstBySocialIdOrderByIdDesc(socialProfileEntity.getSocialId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST, "Social profile not found"));

        if(socialProfileEntity.getGender() != null) {
            socialProfileCheck.setGender(socialProfileEntity.getGender());
        }
        if(socialProfileEntity.getPhone() != null) {
            socialProfileCheck.setPhone(socialProfileEntity.getPhone());
        }
        if(socialProfileEntity.getBirthYear() != null) {
            socialProfileCheck.setBirthYear(socialProfileEntity.getBirthYear());
        }
        if(socialProfileEntity.getBirth() != null) {
            socialProfileCheck.setBirth(socialProfileEntity.getBirth());
        }
        if(socialProfileEntity.getServiceTerms() != null) {
            socialProfileCheck.setServiceTerms(socialProfileEntity.getServiceTerms());
        }
        if(socialProfileEntity.getPrivacyTerms() != null) {
            socialProfileCheck.setPrivacyTerms(socialProfileEntity.getPrivacyTerms());
        }
        if(socialProfileEntity.getMarketingOptInTerms() != null) {
            socialProfileCheck.setMarketingOptInTerms(socialProfileEntity.getMarketingOptInTerms());
        }
        if(socialProfileEntity.getThirdPartyTerms() != null) {
            socialProfileCheck.setThirdPartyTerms(socialProfileEntity.getThirdPartyTerms());
        }

        return socialProfileCheck;
    }

    @Transactional
    public SocialProfileEntity showMe(SocialProfileEntity socialProfileEntity) {
        var socialProfileCheck = socialProfileRepository.findFirstBySocialIdOrderByIdDesc(socialProfileEntity.getSocialId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST, "Social profile not found"));

        return socialProfileCheck;
    }
}
