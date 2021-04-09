package file;

public class XMLGenerationException extends Exception {
    public XMLGenerationException() {
        super();
    }

    public XMLGenerationException(String message) {
        super(message);
    }

    public XMLGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLGenerationException(Throwable cause) {
        super(cause);
    }

    protected XMLGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
