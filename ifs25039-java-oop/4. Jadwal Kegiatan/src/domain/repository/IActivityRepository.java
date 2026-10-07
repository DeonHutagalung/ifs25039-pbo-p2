package domain.repository;

import domain.entity.Activity;
import java.util.List;
import java.util.Optional;

public interface IActivityRepository {
    Activity save(String activityTitle, String weekday, String startTime);
    boolean update(int identifier, String activityTitle, String weekday, String startTime);
    List<Activity> findAll();
    Optional<Activity> findById(int identifier);
    boolean delete(int identifier);
}