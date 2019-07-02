package mx.tmsanchez.reactdemo.repository;

import mx.tmsanchez.reactdemo.model.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, String> {
}
