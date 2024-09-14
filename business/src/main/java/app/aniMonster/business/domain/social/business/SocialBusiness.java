package app.aniMonster.business.domain.social.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.social.converter.SocialConverter;
import app.aniMonster.business.domain.social.model.SocialRefreshRequest;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.domain.social.model.SocialSignRequest;
import app.aniMonster.business.domain.social.service.SocialService;
import app.aniMonster.business.logic.jwt.business.JwtBusiness;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Business
@Slf4j
public class SocialBusiness {

    private final SocialService socialService;
    private final SocialConverter socialConverter;
    private final JwtBusiness jwtBusiness;

    public JwtResponse sign(SocialSignRequest request) {
        var entity = socialConverter.toEntity(request);
        var usedEntity = socialService.sign(entity);
        var response = socialConverter.toSignResponse(usedEntity);
        return jwtBusiness.issueToken(response);
    }

    public SocialResponse showMe(String id) {
        var entity = socialConverter.toEntity(id);
        var usedEntity = socialService.showMe(entity);
        return socialConverter.toResponse(usedEntity);
    }

    public JwtResponse refreshToken(SocialRefreshRequest request ) {
        var socialResponse = jwtBusiness.validateRefreshToken(request.getRefresh_token());
        var entity = socialConverter.toEntity(socialResponse);
        var usedEntity = socialService.showMe(entity);
        var response = socialConverter.toSignResponse(usedEntity);
        return jwtBusiness.issueToken(response);
    }
}
