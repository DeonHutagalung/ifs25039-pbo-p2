package domain.repository;

import domain.entity.Guest;
import java.util.List;
import java.util.Optional;

public interface IGuestRepository {
    Guest save(String guestName, String purpose);
    List<Guest> findAll();
    Optional<Guest> findById(int identifier);
    boolean delete(int identifier);
}