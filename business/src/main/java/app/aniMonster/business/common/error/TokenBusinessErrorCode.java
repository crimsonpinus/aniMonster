package app.aniMonster.business.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Token 의 경우 2000번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum TokenBusinessErrorCode implements BusinessErrorCodeInterface{

    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 2001, "만료된 토큰"),
    UNKNOWN_TOKEN(400, 2002, "알수없는 토큰 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증 헤더 토큰 없음")

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
