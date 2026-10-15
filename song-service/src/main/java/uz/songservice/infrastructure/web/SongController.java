package uz.songservice.infrastructure.web;

import java.util.List;
import java.util.Arrays;
import uz.songservice.domain.Song;
import org.springframework.http.HttpStatus;
import uz.songservice.application.SongService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public SongsDeletionResponse deleteSongMetadata(@RequestParam String ids) {
        List<Long> deletedSongs = songService.deleteSongs(parseIds(ids));
        return new SongsDeletionResponse(deletedSongs);
    }

    private List<Long> parseIds(String ids) {
        if (ids.isBlank() || ids.length() >= 2)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "id must contain a CSV of IDs shorter than 200 characters"
            );

        try {
            List<Long> parsedIds = Arrays.stream(ids.split(",",-1))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .distinct()
                    .toList();
            if (parsedIds.stream().anyMatch(id -> id <= 0))
                throw new NumberFormatException();
            return parsedIds;
        } catch (NumberFormatException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "id must contain positive integer IDs"
            );
        }
    }
}