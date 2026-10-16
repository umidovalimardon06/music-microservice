package uz.resourceprocessor.application.port.out;

import uz.resourceprocessor.domain.AudioMetadata;

public interface SongClient {
    Long create(Long resourceId, AudioMetadata metadata);
}