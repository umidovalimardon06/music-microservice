package uz.resourceservice.infrasructure.persistance.mapper;

import uz.resourceservice.domain.AudioResource;
import org.springframework.stereotype.Component;
import uz.resourceservice.infrasructure.persistance.entity.AudioResourceEntity;

@Component
public class AudioResourcePersistenceMapper {
    public AudioResourceEntity toEntity(AudioResource resource) {
        AudioResourceEntity entity = new AudioResourceEntity();
        entity.setId(resource.getId());
        entity.setBucket(resource.getBucket());
        entity.setObjectKey(resource.getObjectKey());
        entity.setSizeBytes(resource.getSizeBytes());
        return entity;
    }

    public AudioResource toDomain(AudioResourceEntity entity) {
        return AudioResource.recreate(
                entity.getId(),
                entity.getBucket(),
                entity.getObjectKey(),
                entity.getSizeBytes()
        );
    }
}