package exceptions;

public class NotImplementedException extends Exception {
    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, Exception e) {
        super(message, e);
    }
}
