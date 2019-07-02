package mx.tmsanchez.reactdemo.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import mx.tmsanchez.reactdemo.model.Photo;
import mx.tmsanchez.reactdemo.model.Post;
import mx.tmsanchez.reactdemo.model.Transaction;
import mx.tmsanchez.reactdemo.model.TransactionRequest;
import mx.tmsanchez.reactdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionService implements ITransactionService {

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
        return saveTransaction(transaction)
                .flatMap(this::getPost);
    }


    private Mono<Transaction> saveTransaction(Transaction transaction) {
        log.info("Saving transaction");
        return transactionRepository.save(transaction);
    }

    private Mono<Transaction> getPost(Transaction transaction) {
        log.info("Getting post ");
        return postService.getPost(transaction.getPostId())
                .flatMap( post -> updatePostTime(transaction, post));
    }

    private Mono<Transaction> updatePostTime(Transaction transaction, Post post) {
        log.info("Updating transaction with post");
        transaction.setPostAtTime(LocalDateTime.now());
        transaction.setPost(post);
        return transactionRepository.save(transaction)
                .flatMap( trans -> getPhoto(trans, post));
    }


    private Mono<Transaction> getPhoto(Transaction transaction, Post post) {
        log.info("Getting photo");
        return photoService.getPhoto(post.getUserId())
                .flatMap( photo -> updatePhotoTime(transaction, photo));
    }

    private Mono<Transaction> updatePhotoTime(Transaction transaction, Photo photo) {
        log.info("Updating transaction with photo");
        transaction.setPhotoAtTime(LocalDateTime.now());
        transaction.setPhoto(photo);
        return transactionRepository.save(transaction);
    }

}
