package uz.resourceservice.application;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import uz.resourceservice.domain.AudioResource;
import uz.resourceservice.application.port.AudioStorage;
import uz.resourceservice.domain.AudioResourceRepository;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AudioService {
    private final AudioResourceRepository repository;
    private final AudioStorage storage;
    private final String bucket;

    public AudioService(
            AudioResourceRepository repository,
            AudioStorage storage,
            @Value("${storage.s3.bucket}") String bucket) {
        this.repository = repository;
        this.storage = storage;
        this.bucket = bucket;
    }

    public AudioResource upload(byte[] audio) {
        if (audio == null || audio.length == 0) throw new IllegalArgumentException("Audio must not be empty");
        String objectKey = "resources/" + UUID.randomUUID() + ".mp3";
        AudioResource resource = AudioResource.create(
                bucket,
                objectKey,
                audio.length
        );
        storage.upload(bucket,objectKey,audio);
        return repository.save(resource);
    }

    public byte[] download(Long id) {
        AudioResource resource = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
        return storage.download(resource.getBucket(), resource.getObjectKey());
    }

    public Integer deleteById(Long id) {
        AudioResource resource = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
        storage.delete(resource.getBucket(), resource.getObjectKey());
        repository.deleteById(id);
        return id.intValue();
    }
}