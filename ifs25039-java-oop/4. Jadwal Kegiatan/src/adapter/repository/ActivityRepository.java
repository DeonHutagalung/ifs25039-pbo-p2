package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepository implements IActivityRepository {
    private final List<Activity> events = new ArrayList<>();
    private int nextIdentifier = 1;

    @Override
    public Activity save(String activityTitle, String weekday, String startTime) {
        Activity event = new Activity(nextIdentifier++, activityTitle, weekday, startTime);
        events.add(event);
        return event;
    }

    @Override
    public boolean update(int identifier, String activityTitle, String weekday, String startTime) {
        Optional<Activity> eventOption = findById(identifier);
        if (eventOption.isPresent()) {
            Activity previousEvent = eventOption.get();
            int position = events.indexOf(previousEvent);
            Activity replacementEvent = new Activity(previousEvent.getId(), activityTitle, weekday, startTime);
            events.set(position, replacementEvent);
            return true;
        }
        return false;
    }

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public Optional<Activity> findById(int identifier) {
        return events.stream().filter(a -> a.getId() == identifier).findFirst();
    }

    @Override
    public boolean delete(int identifier) {
        Optional<Activity> event = findById(identifier);
        if (event.isPresent()) {
            events.remove(event.get());
            return true;
        }
        return false;
    }
}