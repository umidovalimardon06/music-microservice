package uz.resourceprocessor.application.exception;

public class FailedExtractionException extends RuntimeException {
    public FailedExtractionException(String message, Exception exception) {
        super(message);
    }
}
