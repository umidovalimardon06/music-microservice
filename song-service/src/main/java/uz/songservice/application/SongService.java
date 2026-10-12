package uz.songservice.application;

import uz.songservice.domain.Song;
import uz.songservice.domain.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public Song createSong(Song song) {
        return repository.save(song);
    }

    public Song getSong(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));
    }

    public List<Long> deleteSongs(List<Long> ids) {
        return repository.deleteByIds(ids);
    }
}