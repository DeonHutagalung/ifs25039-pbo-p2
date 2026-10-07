package domain.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {
    Transaction save(String description, long value, TransactionType category);
    List<Transaction> findAll();
    Optional<Transaction> findById(int identifier);
    boolean delete(int identifier);
}