package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Photo;
import mx.tmsanchez.reactdemo.model.Post;
import mx.tmsanchez.reactdemo.model.Transaction;
import mx.tmsanchez.reactdemo.model.TransactionRequest;
import mx.tmsanchez.reactdemo.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransServiceUnitTest {

    @MockBean
    private IPostService postService;

    @MockBean
    private IPhotoService photoService;

    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    private ITransService transactionService;

    @Test
    public void shouldRetrieveTransaction() {
        Mockito.when(postService.getPost(any(Integer.class)))
                .thenReturn(creteMockPost());

        Mockito.when(photoService.getPhoto(any(Integer.class)))
                .thenReturn(creteMockPhoto());

        Mockito.when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(createMockTrans());

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setPostId(22);

        Mono<Transaction> monoTransaction = transactionService.postTransaction(transactionRequest);

        StepVerifier.create(monoTransaction)
                .expectNextMatches(this::transactionMatches)
                .verifyComplete();
    }

    private boolean transactionMatches(Transaction transaction) {
        return transaction.getPost().equals(PostServiceUnitTest.creteMockPost());
    }

    private Mono<Post> creteMockPost() {
        return Mono.just(PostServiceUnitTest.creteMockPost());
    }

    private Mono<Photo> creteMockPhoto() {
        return Mono.just(PhotoServiceUnitTest.creteMockPhoto());
    }

    private Mono<Transaction> createMockTrans() {
        Transaction transaction = new Transaction();
        transaction.setId("5d1acbbb54031f17dc1d482e");
        transaction.setStatus("Created");
        return Mono.just(transaction);
    }

    @TestConfiguration
    private static class SetupTest {
        @Bean
        public ITransService transactionService() {
            return new TransService();
        }
    }
}
