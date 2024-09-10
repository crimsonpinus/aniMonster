package app.aniMonster.business.logic.token.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.token.controller.model.TokenResponse;
import app.aniMonster.business.logic.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ) {
        Objects.requireNonNull(accessToken, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken, ()->{
            throw new BusinessException(BusinessErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpireAt(accessToken.getExpiresAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpireAt(refreshToken.getExpiresAt())
                .build();
    }
}
