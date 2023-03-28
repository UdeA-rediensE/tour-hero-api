package co.udea.api.hero.exception;

/**
 * Exception for duplicated data in the application
 */
public class BusinessException extends GeneralRuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, String translationKey) {
        super(message, translationKey);
    }

}
