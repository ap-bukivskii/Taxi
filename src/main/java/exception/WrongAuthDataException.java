package exception;

public class WrongAuthDataException extends RuntimeException  {
    public WrongAuthDataException() {
    }

    public WrongAuthDataException(String message) {
        super(message);
    }

    public WrongAuthDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
