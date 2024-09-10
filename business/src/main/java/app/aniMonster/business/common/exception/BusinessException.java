package app.aniMonster.business.common.exception;

import app.aniMonster.business.common.error.BusinessErrorCodeInterface;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException implements BusinessErrorCodeInterface {

    private final BusinessErrorCodeInterface businessErrorCodeInterface;

    private final String errorDescription;

    public BusinessException(BusinessErrorCodeInterface businessErrorCodeInterface) {
        super(businessErrorCodeInterface.getDescription());
        this.businessErrorCodeInterface = businessErrorCodeInterface;
        this.errorDescription = businessErrorCodeInterface.getDescription();
    }

    public BusinessException(BusinessErrorCodeInterface businessErrorCodeInterface, String errorDescription) {
        super(errorDescription);
        this.businessErrorCodeInterface = businessErrorCodeInterface;
        this.errorDescription = errorDescription;
    }

    public BusinessException(BusinessErrorCodeInterface businessErrorCodeInterface, Throwable throwable) {
        super(throwable);
        this.businessErrorCodeInterface = businessErrorCodeInterface;
        this.errorDescription = businessErrorCodeInterface.getDescription();
    }

    public BusinessException(BusinessErrorCodeInterface businessErrorCodeInterface, String errorDescription, Throwable throwable) {
        super(throwable);
        this.businessErrorCodeInterface = businessErrorCodeInterface;
        this.errorDescription = errorDescription;
    }

    public Integer httpStatusCode;
    public Integer errorCode;
    public String description;


}
