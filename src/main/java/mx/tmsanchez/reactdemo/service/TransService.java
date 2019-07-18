package mx.tmsanchez.reactdemo.service;

import lombok.extern.slf4j.Slf4j;
import mx.tmsanchez.reactdemo.model.Transaction;
import mx.tmsanchez.reactdemo.model.TransactionRequest;
import mx.tmsanchez.reactdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransService implements ITransService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private IPostService postService;

    @Autowired
    private IPhotoService photoService;

    @Override
    public Mono<Transaction> postTransaction(TransactionRequest transactionRequest) {
        log.info("Inside postTransaction");
        Transaction transaction = new Transaction();
        transaction.setPostId(transactionRequest.getPostId());
        transaction.setStatus("Created");
        transaction.setCreationTime(LocalDateTime.now());
        return manageTransaction(transaction);
    }

    private Mono<Transaction> manageTransaction(Transaction transaction) {
        log.info("Managing transaction");
        log.info("Saving transaction");
        return transactionRepository.save(transaction)
                .flatMap(savedTrans -> {
                            log.info("Getting post");
                            return postService.getPost(savedTrans.getPostId())
                                    .flatMap(post -> {
                                                log.info("Updating Post info");
                                                savedTrans.setPostAtTime(LocalDateTime.now());
                                                savedTrans.setPost(post);
                                                return transactionRepository.save(savedTrans)
                                                        .flatMap(postTrans -> {
                                                                    log.info("getting photo");
                                                                    return photoService.getPhoto(post.getUserId())
                                                                            .flatMap(photo -> {
                                                                                        log.info("Updating transaction with photo");
                                                                                        postTrans.setPhotoAtTime(LocalDateTime.now());
                                                                                        postTrans.setPhoto(photo);
                                                                                        return transactionRepository.save(postTrans);
                                                                                    }
                                                                            );

                                                                }
                                                        );
                                            }
                                    );
                        }
                );

    }

}


