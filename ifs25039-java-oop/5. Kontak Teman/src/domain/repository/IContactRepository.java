package domain.repository;

import domain.entity.Contact;
import java.util.List;
import java.util.Optional;

public interface IContactRepository {
    Contact save(String fullName, String phoneNumber, String emailAddress);
    boolean update(int identifier, String fullName, String phoneNumber, String emailAddress);
    List<Contact> findAll();
    Optional<Contact> findById(int identifier);
    boolean delete(int identifier);
}