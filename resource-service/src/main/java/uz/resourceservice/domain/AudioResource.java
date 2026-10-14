package uz.resourceservice.domain;

public class AudioResource {
    private Long id;
    private String bucket;
    private String objectKey;
    private Long sizeBytes;

    private AudioResource(Long id, String bucket, String objectKey, Long sizeBytes) {
        this.id = id;
        this.bucket = bucket;
        this.objectKey = objectKey;
        this.sizeBytes = sizeBytes;
    }

    public static AudioResource create(String bucket, String objectKey, long sizeBytes) {
        return new AudioResource(null, bucket, objectKey, sizeBytes);
    }

    public static AudioResource recreate(Long id, String bucket, String objectKey, Long sizeBytes) {
        return new AudioResource(id, bucket, objectKey, sizeBytes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public Long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(Long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }
}