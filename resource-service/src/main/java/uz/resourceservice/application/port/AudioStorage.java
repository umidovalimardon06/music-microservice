package uz.resourceservice.application.port;

public interface AudioStorage {
    void upload(String bucket, String objectKey, byte[] audio);
    byte[] download(String bucket, String objectKey);
    void delete(String bucket, String objectKey);
}
