package app.aniMonster.business.logic.jwt.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.logic.jwt.converter.JwtConvertor;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import app.aniMonster.business.logic.jwt.service.JwtService;
import app.aniMonster.business.logic.token.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Business
public class JwtBusiness {
    private final JwtService jwtService;
    private final JwtConvertor jwtConvertor;
    private final ObjectMapper objectMapper;

    public JwtResponse issueToken(SocialResponse response) {
        return Optional.ofNullable(response)
                .map(it ->{
                    var mapData = objectMapper.convertValue(it, HashMap.class);
                    var accessToken = jwtService.issueAccessToken(mapData);
                    var refreshToken = jwtService.issueRefreshToken(mapData);
                    return jwtConvertor.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Response is null"));
    }
}
