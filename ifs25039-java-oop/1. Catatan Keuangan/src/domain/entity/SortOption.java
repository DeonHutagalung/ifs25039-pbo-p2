package domain.entity;

import java.util.Comparator;

public enum SortOption {
    AMOUNT_ASC(Comparator.comparingLong(Transaction::getAmount)),
    AMOUNT_DESC(Comparator.comparingLong(Transaction::getAmount).reversed()),
    INCOME_FIRST(
        Comparator.comparingInt((Transaction t) -> t.getType() == TransactionType.INCOME ? 0 : 1)
                  .thenComparingInt(Transaction::getId)
    ),
    EXPENSE_FIRST(
        Comparator.comparingInt((Transaction t) -> t.getType() == TransactionType.EXPENSE ? 0 : 1)
                  .thenComparingInt(Transaction::getId)
    );

    private final Comparator<Transaction> comparator;

    SortOption(Comparator<Transaction> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Transaction> getComparator() {
        return comparator;
    }
}