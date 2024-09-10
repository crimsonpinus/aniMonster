package app.aniMonster.api.common.exception;


import app.aniMonster.api.common.error.ErrorCodeInterface;

public interface ApiExceptionInterface {

    ErrorCodeInterface getErrorCodeInterface();

    String getErrorDescription();
}
