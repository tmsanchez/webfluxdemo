package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Transaction;
import mx.tmsanchez.reactdemo.model.TransactionRequest;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    Mono<Transaction> postTransaction(TransactionRequest transactionRequest);
}
