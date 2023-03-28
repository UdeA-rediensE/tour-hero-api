package co.udea.api.hero.exception;

/**
 * Generic abstract exception created to identify all Exceptions.
 */
public class GeneralRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private String translationKey;


    public GeneralRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public GeneralRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }


    public GeneralRuntimeException(String message, String translationKey) {
        super(message);
        this.message = message;
        this.translationKey = translationKey;
    }


    public String getTranslationKey() {
        return translationKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
