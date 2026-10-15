package uz.songservice.application;

import java.util.List;
import uz.songservice.domain.Song;
import uz.songservice.domain.SongRepository;
import org.springframework.stereotype.Service;
import uz.songservice.application.exception.SongNotFoundException;

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
        return repository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
    }

    public List<Long> deleteSongs(List<Long> ids) {
        return repository.deleteByIds(ids);
    }
}