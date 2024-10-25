package app.aniMonster.business.logic.jwt.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.jwt.model.JwtAdminResponse;
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
                .access_token(accessToken.getToken())
                .access_token_expire_at(accessToken.getExpiresAt())
                .refresh_token(refreshToken.getToken())
                .refresh_token_expire_at(refreshToken.getExpiresAt())
                .build();
    }

    public JwtAdminResponse toResponse(
            TokenModel adminToken
    ) {
        Objects.requireNonNull(adminToken, () -> {
            throw new BusinessException(BusinessErrorCode.NULL_POINT);
        });

        return JwtAdminResponse.builder()
                .admin_token(adminToken.getToken())
                .access_token_expire_at(adminToken.getExpiresAt())
                .build();
    }
}
