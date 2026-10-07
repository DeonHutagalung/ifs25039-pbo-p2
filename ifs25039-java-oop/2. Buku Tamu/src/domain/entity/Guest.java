package domain.entity;

public class Guest {
    private final int identifier;
    private final String guestName;
    private final String purpose;

    public Guest(int identifier, String guestName, String purpose) {
        this.identifier = identifier;
        this.guestName = guestName;
        this.purpose = purpose;
    }

    public int getId() {
        return identifier;
    }
    public String getName() {
        return guestName;
    }
    public String getPurpose() {
        return purpose;
    }
}