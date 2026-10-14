package uz.resourceservice.infrasructure.persistance.adapter;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import uz.resourceservice.application.port.AudioStorage;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

@Component
public class S3AudioStorageAdapter implements AudioStorage {
    private final S3Client s3Client;

    public S3AudioStorageAdapter(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void upload(String bucket, String objectKey, byte[] audio) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(objectKey)
                .contentType("audio/mpeg")
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(audio));
    }

    @Override
    public byte[] download(String bucket, String objectKey) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(objectKey)
                .build();
        return s3Client.getObjectAsBytes(request).asByteArray();
    }

    @Override
    public void delete(String bucket, String objectKey) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(objectKey)
                .build();
        s3Client.deleteObject(request);
    }
}