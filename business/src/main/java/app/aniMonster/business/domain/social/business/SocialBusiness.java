package app.aniMonster.business.domain.social.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.social.converter.SocialConverter;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.domain.social.model.SocialSignRequest;
import app.aniMonster.business.domain.social.service.SocialService;
import app.aniMonster.business.logic.jwt.business.JwtBusiness;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import app.aniMonster.business.logic.token.business.TokenBusiness;
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
        var response = socialConverter.toResponse(usedEntity);
        return jwtBusiness.issueToken(response);
    }

}
