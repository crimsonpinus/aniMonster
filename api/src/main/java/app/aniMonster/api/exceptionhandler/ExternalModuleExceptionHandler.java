package app.aniMonster.api.exceptionhandler;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
/**
 * 외부모듈 예외 처리 로직
 * 현재는 business관련 예외 처리
 */
public class ExternalModuleExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Api<Object>> handleBudinrddException(
            BusinessException businessException
    ) {
        log.error(businessException.getMessage());

        var errorCode = businessException.getBusinessErrorCodeInterface();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(
                        Api.ERROR(errorCode.getErrorCode(),errorCode.getDescription(), businessException.getErrorDescription())
                );
    }
}