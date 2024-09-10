package app.aniMonster.business.logic.token.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.token.controller.model.TokenResponse;
import app.aniMonster.business.logic.token.converter.TokenConverter;
import app.aniMonster.business.logic.token.service.TokenService;
import app.aniMonster.business.domain.user.model.UserResponse;
import app.aniMonster.postgresql.db.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;

    private final TokenConverter tokenConverter;

    /**
     * 1. user entity userId 추출
     * 2. access, refresh token 발행
     * 3. converter -> token response 로 변형
     * @param userEntity
     * @return
     */
    public TokenResponse issueToken(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(ue -> {
                    return ue.getId();
                })
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(
                        ()-> new BusinessException(BusinessErrorCode.NULL_POINT)
                );
    }

    public TokenResponse issueToken(UserResponse userResponse) {
        return Optional.ofNullable(userResponse)
                .map(ue -> {
                    return ue.getId();
                })
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(
                        ()-> new BusinessException(BusinessErrorCode.NULL_POINT)
                );
    }

    public Long validationAccessToken(String accessToken) {
        var userId = tokenService.validateAccessToken(accessToken);
        return userId;
    }
}
