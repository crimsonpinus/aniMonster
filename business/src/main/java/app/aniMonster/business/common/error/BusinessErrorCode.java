package app.aniMonster.business.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BusinessErrorCode implements BusinessErrorCodeInterface {

    OK(200, 200, "성공"),
    BAD_REQUEST(400, 400, "잘못된 요청"),
    //INTERNAL_SERVER_ERROR: 500
    SERVER_ERROR(500, 500, "서버에러"),
    NULL_POINT(500, 512, "Null Point")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    //Getter로 해결
//    @Override
//    public Integer getHttpStatusCode() {
//        return this.httpStatusCode;
//    }
//
//    @Override
//    public Integer getErrorCode() {
//        return this.errorCode;
//    }
//
//    @Override
//    public String getDescription() {
//        return this.description;
//    }
}
