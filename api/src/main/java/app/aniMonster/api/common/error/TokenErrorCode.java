package app.aniMonster.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User의 경우 1000번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeInterface {

    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증 헤더 토큰 없음")

            ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
