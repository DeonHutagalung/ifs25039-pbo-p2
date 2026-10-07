package usecase;

import domain.entity.Activity;
import domain.entity.ActivitySortOption;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActivityUseCase {
    private final IActivityRepository dataStore;

    public ActivityUseCase(IActivityRepository dataStore) {
        this.dataStore = dataStore;
    }

    public Activity addActivity(String activityTitle, String weekday, String startTime) {
        return dataStore.save(activityTitle, weekday, startTime);
    }

    public boolean updateActivity(int identifier, String activityTitle, String weekday, String startTime) {
        return dataStore.update(identifier, activityTitle, weekday, startTime);
    }

    public Optional<Activity> getActivityById(int identifier) {
        return dataStore.findById(identifier);
    }

    public List<Activity> getAllActivities() {
        return dataStore.findAll();
    }

    public List<Activity> getSortedActivities(ActivitySortOption ordering) {
        List<Activity> events = dataStore.findAll();
        events.sort(ordering.getComparator());
        return events;
    }

    public List<Activity> searchByTitle(String query) {
        return dataStore.findAll().stream()
            .filter(event -> event.getTitle().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteActivity(int identifier) {
        return dataStore.delete(identifier);
    }
}