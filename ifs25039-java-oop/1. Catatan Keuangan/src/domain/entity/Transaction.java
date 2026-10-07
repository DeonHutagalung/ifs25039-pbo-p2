package domain.entity;

public class Transaction {
    private final int identifier;
    private final String description;
    private final long value;
    private final TransactionType category;

    public Transaction(int identifier, String description, long value, TransactionType category) {
        this.identifier = identifier;
        this.description = description;
        this.value = value;
        this.category = category;
    }

    public int getId() {
        return identifier;
    }
    public String getDescription() {
        return description;
    }
    public long getAmount() {
        return value;
    }
    public TransactionType getType() {
        return category;
    }
}