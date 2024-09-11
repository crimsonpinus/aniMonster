package app.aniMonster.business.logic.jwt.helper;


import app.aniMonster.business.logic.jwt.model.TokenModel;

import java.util.Map;

public interface TokenHelperIfs {

    TokenModel issueAccessToken(Map<String, Object> data);

    TokenModel issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}
