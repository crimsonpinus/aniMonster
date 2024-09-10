package app.aniMonster.business.common.exception;


import app.aniMonster.business.common.error.BusinessErrorCodeInterface;

public interface BusinessExceptionInterface {

    BusinessErrorCodeInterface getErrorCodeInterface();

    String getErrorDescription();
}
