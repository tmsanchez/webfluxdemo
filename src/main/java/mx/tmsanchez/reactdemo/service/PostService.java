package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PostService implements IPostService{

    private static final String POST_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String GET_POST_URI = "/posts/%s";

    private final WebClient webClient;

    public PostService() {
        this.webClient = WebClient.builder()
                .baseUrl(POST_BASE_URL)
                .build();
    }

    @Override
    public Mono<Post> getPost(Integer id) {
       return webClient.get()
               .uri(String.format(GET_POST_URI,id))
               .retrieve()
               .bodyToMono(Post.class);
    }
}
