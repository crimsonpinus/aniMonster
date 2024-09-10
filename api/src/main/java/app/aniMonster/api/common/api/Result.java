package app.aniMonster.api.common.api;

import app.aniMonster.api.common.error.ErrorCode;
import app.aniMonster.api.common.error.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;

    public static Result OK(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface){
        return Result.builder()
                .resultCode(errorCodeInterface.getErrorCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, Throwable throwable){
        return Result.builder()
                .resultCode(errorCodeInterface.getErrorCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription(throwable.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, String description){
        return Result.builder()
                .resultCode(errorCodeInterface.getErrorCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription(description)
                .build();
    }

    //외부모듈 오류 수신
    public static Result ERROR(Integer errCode, String errCodeDescrition, String description){
        return Result.builder()
                .resultCode(errCode)
                .resultMessage(errCodeDescrition)
                .resultDescription(description)
                .build();
    }
}
