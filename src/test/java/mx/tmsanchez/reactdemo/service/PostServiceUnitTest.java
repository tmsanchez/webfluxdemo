package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Post;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostServiceUnitTest {
    private static final String MOCK_RESPONSE_JSON =
            "{\"userId\": 3,\"id\": 22,\"title\": \"title\",\"body\": \"body\"}";



    @Autowired
    private IPostService postService;

    @Test
    public void shouldRetrievePost() {

        Post expectedPost = creteMockPost();

        Mono<Post> monoPost = postService.getPost(22);

        StepVerifier.create(monoPost)
                .expectNext(expectedPost)
                .verifyComplete();

    }

    public static Post creteMockPost() {
        Post post = new Post();
        post.setId(22);
        post.setUserId(3);
        post.setTitle("dolor sint quo a velit explicabo quia nam");
        post.setBody("eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");
        return post;
    }

    @TestConfiguration
    private static class SetupTest {
        @Bean
        public IPostService postService() {
            return new PostService();
        }
    }
}
