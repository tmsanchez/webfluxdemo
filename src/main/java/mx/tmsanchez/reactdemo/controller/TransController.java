package mx.tmsanchez.reactdemo.controller;

import mx.tmsanchez.reactdemo.model.Transaction;
import mx.tmsanchez.reactdemo.model.TransactionRequest;
import mx.tmsanchez.reactdemo.service.ITransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/trans")
public class TransController {
    @Autowired
    private ITransService transService;

    @PostMapping
    public Mono<Transaction> postTransaction(
            @RequestBody TransactionRequest transactionRequest) {
        return transService.postTransaction(transactionRequest);
    }
}
