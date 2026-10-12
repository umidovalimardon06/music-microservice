package uz.songservice.infrastructure.persistence.mapper;

import uz.songservice.domain.Song;
import org.springframework.stereotype.Component;
import uz.songservice.infrastructure.persistence.entity.SongJpaEntity;

@Component
public class SongPersistenceMapper {

    public SongJpaEntity toEntity(Song song) {
        SongJpaEntity entity = new SongJpaEntity();
        entity.setId(song.getId());
        entity.setName(song.getName());
        entity.setArtist(song.getArtist());
        entity.setAlbum(song.getAlbum());
        entity.setLength(song.getLength());
        entity.setResourceId(song.getResourceId());
        entity.setReleaseYear(song.getReleaseYear());
        return entity;
    }

    public Song toDomain(SongJpaEntity entity) {
        Song song = new Song();
        song.setId(entity.getId());
        song.setName(entity.getName());
        song.setArtist(entity.getArtist());
        song.setAlbum(entity.getAlbum());
        song.setLength(entity.getLength());
        song.setResourceId(entity.getResourceId());
        song.setReleaseYear(entity.getReleaseYear());
        return song;
    }
}