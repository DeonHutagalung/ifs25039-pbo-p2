package adapter.repository;

import domain.entity.Contact;
import domain.repository.IContactRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository implements IContactRepository {
    private final List<Contact> people = new ArrayList<>();
    private int nextIdentifier = 1;

    @Override
    public Contact save(String fullName, String phoneNumber, String emailAddress) {
        Contact person = new Contact(nextIdentifier++, fullName, phoneNumber, emailAddress);
        people.add(person);
        return person;
    }

    @Override
    public boolean update(int identifier, String fullName, String phoneNumber, String emailAddress) {
        Optional<Contact> personOption = findById(identifier);
        if (personOption.isPresent()) {
            Contact previousPerson = personOption.get();
            int position = people.indexOf(previousPerson);
            Contact replacementPerson = new Contact(previousPerson.getId(), fullName, phoneNumber, emailAddress);
            people.set(position, replacementPerson);
            return true;
        }
        return false;
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(people);
    }

    @Override
    public Optional<Contact> findById(int identifier) {
        return people.stream().filter(c -> c.getId() == identifier).findFirst();
    }

    @Override
    public boolean delete(int identifier) {
        Optional<Contact> person = findById(identifier);
        if (person.isPresent()) {
            people.remove(person.get());
            return true;
        }
        return false;
    }
}