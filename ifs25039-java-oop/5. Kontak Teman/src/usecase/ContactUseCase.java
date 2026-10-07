package usecase;

import domain.entity.Contact;
import domain.entity.ContactSortOption;
import domain.repository.IContactRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContactUseCase {
    private final IContactRepository dataStore;

    public ContactUseCase(IContactRepository dataStore) {
        this.dataStore = dataStore;
    }

    public Contact addContact(String fullName, String phoneNumber, String emailAddress) {
        return dataStore.save(fullName, phoneNumber, emailAddress);
    }

    public boolean updateContact(int identifier, String fullName, String phoneNumber, String emailAddress) {
        return dataStore.update(identifier, fullName, phoneNumber, emailAddress);
    }

    public Optional<Contact> getContactById(int identifier) {
        return dataStore.findById(identifier);
    }

    public List<Contact> getAllContacts() {
        return dataStore.findAll();
    }

    public List<Contact> getSortedContacts(ContactSortOption ordering) {
        List<Contact> people = dataStore.findAll();
        people.sort(ordering.getComparator());
        return people;
    }

    public List<Contact> searchByName(String query) {
        return dataStore.findAll().stream()
            .filter(person -> person.getName().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteContact(int identifier) {
        return dataStore.delete(identifier);
    }
}