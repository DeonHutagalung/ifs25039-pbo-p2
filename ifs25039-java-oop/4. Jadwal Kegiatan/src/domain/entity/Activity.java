package domain.entity;

public class Activity {
    private final int identifier;
    private final String activityTitle;
    private final String weekday;
    private final String startTime;

    public Activity(int identifier, String activityTitle, String weekday, String startTime) {
        this.identifier = identifier;
        this.activityTitle = activityTitle;
        this.weekday = weekday;
        this.startTime = startTime;
    }

    public int getId() {
        return identifier;
    }
    public String getTitle() {
        return activityTitle;
    }
    public String getDay() {
        return weekday;
    }
    public String getTime() {
        return startTime;
    }
}