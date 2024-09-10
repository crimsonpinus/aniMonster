package app.aniMonster.business.logic.token.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.token.ifs.TokenHelperIfs;
import app.aniMonster.business.logic.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * token 관련 도메[인 로직
 */
@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validateAccessToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }
}
