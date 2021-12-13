package exceptions;

public class IncorrectInputException extends Exception {
    public IncorrectInputException(String message) {
        super(message);
    }

    public IncorrectInputException(String message, Exception e) {
        super(message, e);
    }
}
