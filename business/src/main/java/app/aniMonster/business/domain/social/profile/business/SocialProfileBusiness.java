package app.aniMonster.business.domain.social.profile.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.social.profile.converter.SocialProfileConverter;
import app.aniMonster.business.domain.social.profile.model.SocialProfileRequest;
import app.aniMonster.business.domain.social.profile.model.SocialProfileResponse;
import app.aniMonster.business.domain.social.profile.service.SocialProfileService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class SocialProfileBusiness {

    private final SocialProfileService socialProfileService;
    private final SocialProfileConverter socialProfileConverter;

    public SocialProfileResponse update(SocialProfileRequest request) {
        var entity = socialProfileConverter.toEntity(request);
        var usedEntity = socialProfileService.update(entity);
        return  socialProfileConverter.toResponse(usedEntity);
    }

    public SocialProfileResponse showMe(String socialId) {
        System.out.println("------------:"+socialId);
        var entity = socialProfileConverter.toEntity(socialId);
        var usedEntity = socialProfileService.showMe(entity);
        System.out.println("------------:"+usedEntity.getSocialId());
        return  socialProfileConverter.toResponse(usedEntity);
    }
}
