package exceptions;

public class CaclException extends Exception {
    public CaclException(String message) {
        super(message);
    }

    public CaclException(String message, Exception e) {
        super(message, e);
    }
}
