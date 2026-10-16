package uz.resourceprocessor.application.port.out;

public interface ResourceClient {
    byte[] download(Long resourceId);
}