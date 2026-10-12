package uz.songservice.domain;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    Song save(Song song);
    Optional<Song> findById(Long id);
    List<Long> deleteByIds(List<Long> ids);
}