package uz.resourceprocessor.application.port.out;

import uz.resourceprocessor.domain.AudioMetadata;

public interface AudioMetadataExtractor {
    AudioMetadata extract(byte[] audio);
}