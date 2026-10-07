package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepository implements IGuestRepository {
    private final List<Guest> visitors = new ArrayList<>();
    private int nextIdentifier = 1;

    @Override
    public Guest save(String guestName, String purpose) {
        Guest visitor = new Guest(nextIdentifier++, guestName, purpose);
        visitors.add(visitor);
        return visitor;
    }

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>(visitors);
    }

    @Override
    public Optional<Guest> findById(int identifier) {
        return visitors.stream().filter(g -> g.getId() == identifier).findFirst();
    }

    @Override
    public boolean delete(int identifier) {
        Optional<Guest> visitor = findById(identifier);
        if (visitor.isPresent()) {
            visitors.remove(visitor.get());
            return true;
        }
        return false;
    }
}