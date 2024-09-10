package app.aniMonster.api.exceptionhandler;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.api.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE + 1)//최우선 처리
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Api<Object>> handleApiException(
            ApiException apiException
    ) {
        log.error(apiException.getMessage());

        var errorCode = apiException.getErrorCodeInterface();
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(
                        Api.ERROR(errorCode, errorCode.getDescription())
                );
    }
}