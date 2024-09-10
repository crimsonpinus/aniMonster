package app.aniMonster.api.common.exception;

import app.aniMonster.api.common.error.ErrorCodeInterface;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException implements ErrorCodeInterface {

    private final ErrorCodeInterface errorCodeInterface;

    private final String errorDescription;

    public ApiException(ErrorCodeInterface errorCodeInterface) {
        super(errorCodeInterface.getDescription());
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorCodeInterface.getDescription();
    }

    public ApiException(ErrorCodeInterface errorCodeInterface, String errorDescription) {
        super(errorDescription);
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeInterface errorCodeInterface, Throwable throwable) {
        super(throwable);
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorCodeInterface.getDescription();
    }

    public ApiException(ErrorCodeInterface errorCodeInterface, String errorDescription, Throwable throwable) {
        super(throwable);
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorDescription;
    }

    public Integer httpStatusCode;
    public Integer errorCode;
    public String description;

}
