package uz.resourceprocessor.infrastructure.config;

import java.time.Duration;
import org.springframework.web.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;

@Configuration
public class HttpClient {
    @Bean
    public RestClient resourceServiceClient(@Value("${clients.resource.url}") String baseUrl) {
        return createHttpClientBasedOnUrl(baseUrl);
    }

    @Bean
    public RestClient songServiceClient(@Value("${clients.song.url}") String baseUrl) {
        return createHttpClientBasedOnUrl(baseUrl);
    }

    private RestClient createHttpClientBasedOnUrl(String baseUrl) {
        java.net.http.HttpClient httpClient = java.net.http.HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);
        return RestClient.builder().baseUrl(baseUrl).requestFactory(factory).build();
    }
}