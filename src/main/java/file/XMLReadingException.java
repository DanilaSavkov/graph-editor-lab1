package file;

public class XMLReadingException extends Exception {
    public XMLReadingException() {
        super();
    }

    public XMLReadingException(String message) {
        super(message);
    }

    public XMLReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLReadingException(Throwable cause) {
        super(cause);
    }

    protected XMLReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
