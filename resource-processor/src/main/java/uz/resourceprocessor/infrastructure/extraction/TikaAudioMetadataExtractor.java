package uz.resourceprocessor.infrastructure.extraction;

import java.util.Locale;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.SAXException;
import java.io.ByteArrayInputStream;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;
import uz.resourceprocessor.domain.AudioMetadata;
import org.apache.tika.metadata.TikaCoreProperties;
import uz.resourceprocessor.application.exception.EmptyAudioException;
import uz.resourceprocessor.application.port.out.AudioMetadataExtractor;
import uz.resourceprocessor.application.exception.FailedExtractionException;

@Component
public class TikaAudioMetadataExtractor implements AudioMetadataExtractor {
    private static final Pattern YEAR_PATTERN = Pattern.compile("^(\\d{4})(?:\\D.*)?$");

    @Override
    public AudioMetadata extract(byte[] audio) {
        if (audio == null || audio.length == 0) throw new EmptyAudioException("Audio must not be empty");
        Metadata metadata = new Metadata();
        try (ByteArrayInputStream input = new ByteArrayInputStream(audio)) {
            new Mp3Parser().parse(input, new DefaultHandler(), metadata, new ParseContext());
        } catch (IOException | SAXException | TikaException exception) {
            throw new FailedExtractionException("Failed to extract MP3 metadata", exception);
        }
        return new AudioMetadata(
                clean(metadata.get(TikaCoreProperties.TITLE)),
                clean(metadata.get(XMPDM.ARTIST)),
                clean(metadata.get(XMPDM.ALBUM)),
                formatDuration(metadata.get(XMPDM.DURATION)),
                parseYear(metadata.get(XMPDM.RELEASE_DATE))
        );
    }

    private String clean(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }

    private Integer parseYear(String value) {
        String cleaned = clean(value);
        if (cleaned == null) return null;
        Matcher matcher = YEAR_PATTERN.matcher(cleaned);
        if (!matcher.matches()) return null;
        int year = Integer.parseInt(matcher.group(1));
        return year > 0 ? year : null;
    }

    private String formatDuration(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            double seconds = Double.parseDouble(value);
            if (!Double.isFinite(seconds) || seconds < 0) return null;
            long totalSeconds = (long) seconds;
            return String.format(Locale.ROOT, "%d:%02d", totalSeconds / 60, totalSeconds % 60);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}