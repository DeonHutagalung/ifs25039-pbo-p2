package domain.entity;

public class Contact {
    private final int identifier;
    private final String fullName;
    private final String phoneNumber;
    private final String emailAddress;

    public Contact(int identifier, String fullName, String phoneNumber, String emailAddress) {
        this.identifier = identifier;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return identifier;
    }
    public String getName() {
        return fullName;
    }
    public String getPhone() {
        return phoneNumber;
    }
    public String getEmail() {
        return emailAddress;
    }
}