package uz.songservice.infrastructure.web;

import java.util.List;
import uz.songservice.domain.Song;
import uz.songservice.application.SongService;
import org.springframework.web.bind.annotation.*;
import uz.songservice.infrastructure.web.dto.SongCreationRequest;
import uz.songservice.infrastructure.web.dto.SongCreationResponse;
import uz.songservice.infrastructure.web.dto.SongMetadataResponse;
import uz.songservice.infrastructure.web.dto.SongsDeletionResponse;

@RestController()
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public SongCreationResponse createSongMetadata(@RequestBody SongCreationRequest request) {
        Song song = new Song();
        song.setName(request.name());
        song.setArtist(request.artist());
        song.setAlbum(request.album());
        song.setLength(request.length());
        song.setResourceId(request.resourceId());
        song.setReleaseYear(request.year());
        Song createdSong = songService.createSong(song);
        return new SongCreationResponse(createdSong.getId());
    }

    @GetMapping("/{id}")
    public SongMetadataResponse getSongMetadata(@PathVariable Long id) {
        Song song = songService.getSong(id);
        return new SongMetadataResponse(
                song.getName(),
                song.getArtist(),
                song.getAlbum(),
                song.getLength(),
                song.getResourceId(),
                song.getReleaseYear()
        );
    }

    @DeleteMapping
    public SongsDeletionResponse deleteSongMetadata(@RequestParam List<Long> ids) {
        List<Long> deletedSongs = songService.deleteSongs(ids);
        return new SongsDeletionResponse(deletedSongs);
    }
}