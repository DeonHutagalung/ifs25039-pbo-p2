package adapter.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> entries = new ArrayList<>();
    private int nextIdentifier = 1;

    @Override
    public Transaction save(String description, long value, TransactionType category) {
        Transaction entry = new Transaction(nextIdentifier++, description, value, category);
        entries.add(entry);
        return entry;
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(entries);
    }

    @Override
    public Optional<Transaction> findById(int identifier) {
        return entries.stream().filter(t -> t.getId() == identifier).findFirst();
    }

    @Override
    public boolean delete(int identifier) {
        Optional<Transaction> entry = findById(identifier);
        if (entry.isPresent()) {
            entries.remove(entry.get());
            return true;
        }
        return false;
    }
}