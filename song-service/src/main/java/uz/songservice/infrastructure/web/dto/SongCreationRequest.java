package uz.songservice.infrastructure.web.dto;

public record SongCreationRequest(
        String name,
        String artist,
        String album,
        String length,
        Integer resourceId,
        Integer year
) {
}