package uz.resourceservice.application.exception;

public class AudioNotFoundException extends RuntimeException {
    public AudioNotFoundException(String message) {
        super(message);
    }
}
