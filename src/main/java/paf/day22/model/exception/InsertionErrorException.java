package paf.day22.model.exception;

public class InsertionErrorException extends RuntimeException {
    public InsertionErrorException() {
        super();
    }
    public InsertionErrorException(String message) {
        super(message);
    }
    public InsertionErrorException(String message, Throwable cause) {
        super(message, cause);  // not really used
    }
}
