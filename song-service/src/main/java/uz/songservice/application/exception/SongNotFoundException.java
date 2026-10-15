package uz.songservice.application.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Long id) {
        super("Song not found with ID: " + id);
    }
}