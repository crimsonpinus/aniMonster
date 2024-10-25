package app.aniMonster.business.logic.jwt.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.admin.model.AdminResponse;
import app.aniMonster.business.domain.social.model.SocialSignResponse;
import app.aniMonster.business.logic.jwt.converter.JwtConvertor;
import app.aniMonster.business.logic.jwt.model.JwtAdminResponse;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import app.aniMonster.business.logic.jwt.service.JwtService;
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

    public JwtResponse issueToken(SocialSignResponse response) {
        return Optional.ofNullable(response)
                .map(it ->{

                    var accessMapData = new HashMap<String, Object>();
                    accessMapData.put("social_id", it.getSocial_id());

                    var accessToken = jwtService.issueAccessToken(accessMapData);

                    var refreshMapData = objectMapper.convertValue(it, HashMap.class);
                    var refreshToken = jwtService.issueRefreshToken(refreshMapData);
                    return jwtConvertor.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Response is null"));
    }

    public JwtAdminResponse issueToken(AdminResponse response) {
        return Optional.ofNullable(response)
                .map(it ->{

                    var accessMapData = new HashMap<String, Object>();
//                    accessMapData.put("social_id", it.getId());
                    accessMapData.put("admin_id", it.getId());
                    accessMapData.put("admin_author", it.getAdmin_author());

                    var adminToken = jwtService.issueAdminToken(accessMapData);


                    return jwtConvertor.toResponse(adminToken);
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Response is null"));
    }

    public Map<String, String> validateToken(String token) {
        var temp = jwtService.validateToken(token);
        System.out.println(temp);
        return temp;
    }
    public SocialSignResponse validateRefreshToken(String refreshToken) {
        return jwtService.validateRefreshToken(refreshToken);
    }
}
