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

    private Integer result_code;

    private String result_message;

    private String result_description;

    public static Result OK(){
        return Result.builder()
                .result_code(ErrorCode.OK.getErrorCode())
                .result_message(ErrorCode.OK.getDescription())
                .result_description("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface){
        return Result.builder()
                .result_code(errorCodeInterface.getErrorCode())
                .result_message(errorCodeInterface.getDescription())
                .result_description("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, Throwable throwable){
        return Result.builder()
                .result_code(errorCodeInterface.getErrorCode())
                .result_message(errorCodeInterface.getDescription())
                .result_description(throwable.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, String description){
        return Result.builder()
                .result_code(errorCodeInterface.getErrorCode())
                .result_message(errorCodeInterface.getDescription())
                .result_description(description)
                .build();
    }

    //외부모듈 오류 수신
    public static Result ERROR(Integer errCode, String errCodeDescrition, String description){
        return Result.builder()
                .result_code(errCode)
                .result_message(errCodeDescrition)
                .result_description(description)
                .build();
    }
}
