package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Photo;
import mx.tmsanchez.reactdemo.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhotoServiceUnitTest {

    @Autowired
    private IPhotoService photoService;

    @Test
    public void shouldRetrievePost() {

        Photo expectedPhoto = creteMockPhoto();

        Mono<Photo> monoPhoto = photoService.getPhoto(3);

        StepVerifier.create(monoPhoto)
                .expectNext(expectedPhoto)
                .verifyComplete();

    }

    public static Photo creteMockPhoto() {
        Photo photo = new Photo();
        photo.setId(21);
        photo.setUserId(3);
        photo.setTitle("repudiandae voluptatem optio est consequatur rem in temporibus et");
        return photo;
    }

    @TestConfiguration
    private static class SetupTest {
        @Bean
        public IPhotoService photoService() {
            return new PhotoService();
        }
    }


}
