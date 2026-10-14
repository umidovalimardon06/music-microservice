package uz.resourceservice.domain;

import java.util.Optional;

public interface AudioResourceRepository {
    AudioResource save(AudioResource resource);
    Optional<AudioResource> findById(Long id);
    void deleteById(Long id);
}
