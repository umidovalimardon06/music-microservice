package uz.resourceprocessor.domain;

import uz.resourceprocessor.application.exception.NegativeReleaseYearException;

public class AudioMetadata {
    private String name;
    private String artist;
    private String album;
    private final String length;
    private Integer releaseYear;

    public AudioMetadata(String name, String artist,
                         String album, String length,
                         Integer releaseYear) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.setReleaseYear(releaseYear);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLength() {
        return length;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        if (releaseYear != null && releaseYear <= 0)
            throw new NegativeReleaseYearException("Release year must be a positive integer");
        this.releaseYear = releaseYear;
    }
}