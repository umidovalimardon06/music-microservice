package uz.resourceprocessor.infrastructure.cilent;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import uz.resourceprocessor.domain.AudioMetadata;
import org.springframework.web.client.RestClient;
import uz.resourceprocessor.application.port.out.SongClient;
import org.springframework.beans.factory.annotation.Qualifier;
import uz.resourceprocessor.infrastructure.cilent.dto.SongCreationRequest;
import uz.resourceprocessor.infrastructure.cilent.dto.SongCreationResponse;

@Component
public class SongHttpClient implements SongClient {
    private final RestClient client;

    public SongHttpClient(@Qualifier("songRestClient") RestClient client) {
        this.client = client;
    }

    @Override
    public Long create(Long resourceId, AudioMetadata metadata) {
        SongCreationRequest request = new SongCreationRequest(
                metadata.getName(),
                metadata.getArtist(),
                metadata.getAlbum(),
                metadata.getLength(),
                Math.toIntExact(resourceId),
                metadata.getReleaseYear()
        );
        SongCreationResponse response = client.post()
                .uri("/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(SongCreationResponse.class);

        if (response == null || response.id() == null)
            throw new IllegalStateException("Song Service returned no song ID");

        return response.id();
    }
}