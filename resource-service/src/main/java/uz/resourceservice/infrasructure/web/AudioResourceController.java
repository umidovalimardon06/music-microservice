package uz.resourceservice.infrasructure.web;

import org.springframework.http.ResponseEntity;
import uz.resourceservice.domain.AudioResource;
import org.springframework.web.bind.annotation.*;
import uz.resourceservice.application.AudioService;
import uz.resourceservice.infrasructure.web.dto.ResourceCreationResponse;
import uz.resourceservice.infrasructure.web.dto.ResourceDeletionResponse;

@RestController
@RequestMapping("/resources")
public class AudioResourceController {
    private final AudioService audioService;

    public AudioResourceController(AudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping(consumes = "audio/mpeg")
    public ResponseEntity<ResourceCreationResponse> uploadResource(@RequestBody byte[] audioData) {
        AudioResource audioResource = this.audioService.upload(audioData);
        return ResponseEntity.ok(new ResourceCreationResponse(audioResource.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadResource(@PathVariable Long id) {
        byte[] audioData = this.audioService.download(id);
        return ResponseEntity.ok().body(audioData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceDeletionResponse> deleteResource(@PathVariable Long id) {
        Integer deletedId = this.audioService.deleteById(id);
        return ResponseEntity.ok(new ResourceDeletionResponse(deletedId));
    }
}