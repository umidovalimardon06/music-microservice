package uz.songservice.infrastructure.persistence.adapter;

import uz.songservice.domain.Song;
import jakarta.transaction.Transactional;
import uz.songservice.domain.SongRepository;
import org.springframework.stereotype.Repository;
import uz.songservice.infrastructure.persistence.entity.SongJpaEntity;
import uz.songservice.infrastructure.persistence.SpringDataSongRepository;
import uz.songservice.infrastructure.persistence.mapper.SongPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional()
public class JpaSongRepositoryAdapter implements SongRepository {

    private final SpringDataSongRepository repository;
    private final SongPersistenceMapper mapper;

    public JpaSongRepositoryAdapter(
            SpringDataSongRepository repository,
            SongPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Song save(Song song) {
        SongJpaEntity entity = mapper.toEntity(song);
        SongJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Long> deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return List.of();
        List<SongJpaEntity> existingSongs = repository.findAllById(ids);

        List<Long> deletedIds = existingSongs.stream()
                .map(SongJpaEntity::getId)
                .sorted()
                .toList();

        repository.deleteAll(existingSongs);
        return deletedIds;
    }
}