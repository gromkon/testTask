package Exceptions;

public class WrongTaskFormatException extends RuntimeException {
    public WrongTaskFormatException(String errorMessage) {
        super(errorMessage);
    }
}
