package uz.resourceprocessor.infrastructure.cilent;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Qualifier;
import uz.resourceprocessor.application.port.out.ResourceClient;
import uz.resourceprocessor.application.exception.EmptyAudioException;

@Component
public class ResourceHttpClient implements ResourceClient {
    private final RestClient client;

    public ResourceHttpClient(
            @Qualifier("resourceServiceClient")RestClient  client) {
        this.client = client;
    }

    @Override
    public byte[] download(Long resourceId) {
        byte[] audio = client.get()
                .uri("/resources/{id}", resourceId)
                .accept(MediaType.parseMediaType("audio/mpeg"))
                .retrieve()
                .body(byte[].class);
        if (audio == null || audio.length == 0)
            throw new EmptyAudioException("Resource Service returned empty audio ID " + resourceId);
        return audio;
    }
}