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

        socialProfileCheck.setGender(socialProfileEntity.getGender());
        socialProfileCheck.setPhone(socialProfileEntity.getPhone());
        socialProfileCheck.setBirthYear(socialProfileEntity.getBirthYear());
        socialProfileCheck.setBirth(socialProfileEntity.getBirth());
        socialProfileCheck.setServiceTerms(socialProfileEntity.getServiceTerms());
        socialProfileCheck.setPrivacyTerms(socialProfileEntity.getPrivacyTerms());
        socialProfileCheck.setMarketingOptInTerms(socialProfileEntity.getMarketingOptInTerms());
        socialProfileCheck.setThirdPartyTerms(socialProfileEntity.getThirdPartyTerms());

        return socialProfileCheck;
    }

    @Transactional
    public SocialProfileEntity showMe(SocialProfileEntity socialProfileEntity) {
        var socialProfileCheck = socialProfileRepository.findFirstBySocialIdOrderByIdDesc(socialProfileEntity.getSocialId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST, "Social profile not found"));

        return socialProfileCheck;
    }
}
