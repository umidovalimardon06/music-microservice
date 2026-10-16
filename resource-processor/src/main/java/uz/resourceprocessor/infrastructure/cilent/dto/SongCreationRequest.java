package uz.resourceprocessor.infrastructure.cilent.dto;

public record SongCreationRequest(
        String name,
        String artist,
        String album,
        String length,
        Integer resourceId,
        Integer year
) {
}