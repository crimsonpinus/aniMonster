package app.aniMonster.business.common.error;

public interface BusinessErrorCodeInterface {
    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
