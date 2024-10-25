package app.aniMonster.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User의 경우 1000번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeInterface {

    USER_NOT_FOUND(400, 1404, "사용자을 찾을 수 없음"),
    USER_AUTH_ERROR(400, 1405, "관리자 권한 필요")

            ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
