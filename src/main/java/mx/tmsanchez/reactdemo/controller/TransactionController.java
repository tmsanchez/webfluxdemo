package mx.tmsanchez.reactdemo.controller;

import mx.tmsanchez.reactdemo.model.Transaction;
import mx.tmsanchez.reactdemo.model.TransactionRequest;
import mx.tmsanchez.reactdemo.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public Mono<Transaction> postTransaction(
            @RequestBody TransactionRequest transactionRequest) {
        return transactionService.postTransaction(transactionRequest);
    }

}
