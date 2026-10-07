package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceUseCase {
    private final ITransactionRepository dataStore;

    public FinanceUseCase(ITransactionRepository dataStore) {
        this.dataStore = dataStore;
    }

    public Transaction addIncome(String description, long value) {
        return dataStore.save(description, value, TransactionType.INCOME);
    }

    public Transaction addExpense(String description, long value) {
        return dataStore.save(description, value, TransactionType.EXPENSE);
    }

    public List<Transaction> getAllTransactions() {
        return dataStore.findAll();
    }

    public List<Transaction> getSortedTransactions(SortOption ordering) {
        List<Transaction> entries = dataStore.findAll();
        entries.sort(ordering.getComparator());
        return entries;
    }

    public List<Transaction> searchByDescription(String query) {
        return dataStore.findAll().stream()
            .filter(t -> t.getDescription().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteTransaction(int identifier) {
        return dataStore.delete(identifier);
    }

    public long getBalance() {
        return dataStore.findAll().stream()
            .mapToLong(t -> t.getType() == TransactionType.INCOME ? t.getAmount() : -t.getAmount())
            .sum();
    }
}