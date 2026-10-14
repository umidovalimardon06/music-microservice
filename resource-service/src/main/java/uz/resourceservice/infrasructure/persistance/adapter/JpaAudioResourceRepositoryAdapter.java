package uz.resourceservice.infrasructure.persistance.adapter;

import java.util.Optional;
import jakarta.transaction.Transactional;
import uz.resourceservice.domain.AudioResource;
import org.springframework.stereotype.Repository;
import uz.resourceservice.domain.AudioResourceRepository;
import uz.resourceservice.infrasructure.persistance.SpringDataAudioResourceRepository;
import uz.resourceservice.infrasructure.persistance.entity.AudioResourceEntity;
import uz.resourceservice.infrasructure.persistance.mapper.AudioResourcePersistenceMapper;

@Repository
@Transactional
public class JpaAudioResourceRepositoryAdapter implements AudioResourceRepository {
    private final SpringDataAudioResourceRepository repository;
    private final AudioResourcePersistenceMapper mapper;

    public JpaAudioResourceRepositoryAdapter(SpringDataAudioResourceRepository repository, AudioResourcePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AudioResource save(AudioResource resource) {
        AudioResourceEntity entity = mapper.toEntity(resource);
        AudioResourceEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AudioResource> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}