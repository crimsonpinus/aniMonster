package app.aniMonster.business.logic.jwt.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.jwt.helper.TokenHelperIfs;
import app.aniMonster.business.logic.jwt.model.TokenModel;
import app.aniMonster.business.logic.token.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
}
