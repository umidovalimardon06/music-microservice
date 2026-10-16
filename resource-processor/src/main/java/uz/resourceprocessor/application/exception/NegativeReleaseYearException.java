package uz.resourceprocessor.application.exception;

public class NegativeReleaseYearException extends RuntimeException {
    public NegativeReleaseYearException(String message) {
        super(message);
    }
}
