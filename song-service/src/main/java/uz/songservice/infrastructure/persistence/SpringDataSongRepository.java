package uz.songservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.songservice.infrastructure.persistence.entity.SongJpaEntity;

public interface SpringDataSongRepository extends JpaRepository<SongJpaEntity, Long> {
}