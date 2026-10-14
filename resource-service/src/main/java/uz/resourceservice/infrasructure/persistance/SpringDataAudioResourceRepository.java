package uz.resourceservice.infrasructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.resourceservice.infrasructure.persistance.entity.AudioResourceEntity;

public interface SpringDataAudioResourceRepository extends JpaRepository<AudioResourceEntity, Long> {
}