package app.aniMonster.business.logic.jwt.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import app.aniMonster.business.logic.jwt.model.TokenModel;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class JwtConvertor {

    public JwtResponse toResponse(
            TokenModel accessToken,
            TokenModel refreshToken
    ) {
        Objects.requireNonNull(accessToken, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT);
        });

        return JwtResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpireAt(accessToken.getExpiresAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpireAt(refreshToken.getExpiresAt())
                .build();
    }
}
