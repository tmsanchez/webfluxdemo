package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class PhotoService implements IPhotoService {
    private static final String POST_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String GET_POST_URI = "/albums/?userId=%s";

    private final WebClient webClient;

    public PhotoService() {
        this.webClient = WebClient.builder()
                .baseUrl(POST_BASE_URL)
                .build();
    }

    @Override
    public Mono<Photo> getPhoto(Integer userId) {
        return webClient.get()
                .uri(String.format(GET_POST_URI,userId))
                .retrieve()
                //.bodyToMono(Photo.class);
                .bodyToFlux(Photo.class)
                .next();
    }
}
