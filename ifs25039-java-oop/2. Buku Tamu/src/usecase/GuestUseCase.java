package usecase;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.List;
import java.util.stream.Collectors;

public class GuestUseCase {
    private final IGuestRepository dataStore;

    public GuestUseCase(IGuestRepository dataStore) {
        this.dataStore = dataStore;
    }

    public Guest registerGuest(String guestName, String purpose) {
        return dataStore.save(guestName, purpose);
    }

    public List<Guest> getAllGuests() {
        return dataStore.findAll();
    }

    public List<Guest> searchByName(String query) {
        return dataStore.findAll().stream()
            .filter(g -> g.getName().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteGuest(int identifier) {
        return dataStore.delete(identifier);
    }
}