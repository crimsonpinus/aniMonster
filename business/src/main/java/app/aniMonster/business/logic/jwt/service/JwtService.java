package app.aniMonster.business.logic.jwt.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.social.model.SocialSignResponse;
import app.aniMonster.business.logic.jwt.helper.TokenHelperIfs;
import app.aniMonster.business.logic.jwt.model.TokenModel;
import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenModel issueAccessToken(Map<String, Object> data) {
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenModel issueRefreshToken(Map<String, Object> data) {
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public String validateToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var id = map.get("social_id");

        Objects.requireNonNull(id, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT,"Invalid token");
        });
        return id.toString();
    }

    public SocialSignResponse validateRefreshToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var id = map.get("social_id").toString();

        var isAdult_str = map.get("is_adult").toString();
        SocialIsAdult isAdult;
        if (isAdult_str.equals("REGISTERED")){
            isAdult = SocialIsAdult.REGISTERED;
        }else if(isAdult_str.equals("UNREGISTERED")){
            isAdult = SocialIsAdult.UNREGISTERED;
        }else{
            throw new BusinessException(BusinessErrorCode.NULL_POINT,"Invalid token");
        }

        var status_str = map.get("status").toString();
        SocialStatus status;
        if (status_str.equals("REGISTERED")){
            status = SocialStatus.REGISTERED;
        }else{
            throw new BusinessException(BusinessErrorCode.NULL_POINT,"Invalid token");
        }
        return SocialSignResponse.builder()
                .socialId(id)
                .status(status)
                .isAdult(isAdult)
                .build();
    }
}
