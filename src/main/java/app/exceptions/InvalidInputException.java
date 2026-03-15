package app.exceptions;

/**
 * Исключение, выбрасываемое при некорректном пользовательском вводе.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}