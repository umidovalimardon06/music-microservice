package uz.resourceprocessor.application;

import org.springframework.stereotype.Service;
import uz.resourceprocessor.domain.AudioMetadata;
import uz.resourceprocessor.application.port.out.SongClient;
import uz.resourceprocessor.application.port.out.ResourceClient;
import uz.resourceprocessor.application.port.out.AudioMetadataExtractor;

@Service
public class ResourceProcessingService {
    private final SongClient songService;
    private final ResourceClient resourceService;
    private final AudioMetadataExtractor extractor;

    public ResourceProcessingService(
            ResourceClient resourceService,
            SongClient songService,
            AudioMetadataExtractor extractor) {
        this.resourceService = resourceService;
        this.songService = songService;
        this.extractor = extractor;
    }

    public Long process(Long resourceId) {
        byte[] audio = resourceService.download(resourceId);
        AudioMetadata metadata = extractor.extract(audio);
        applyDefaults(metadata);
        return songService.create(resourceId, metadata);
    }

    private void applyDefaults(AudioMetadata metadata) {
        if (isBlank(metadata.getName())) metadata.setName("Unknown title");
        if (isBlank(metadata.getArtist())) metadata.setArtist("Unknown artist");
        if (isBlank(metadata.getAlbum())) metadata.setAlbum("Unknown album");
        if (metadata.getReleaseYear() == null || metadata.getReleaseYear() <= 0) metadata.setReleaseYear(1);
    }

    private boolean isBlank(String value) {
        return (value == null || value.isBlank());
    }
}