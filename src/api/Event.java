package api;

import java.sql.Time;
import java.sql.Timestamp;


//duplication of event data nd CourseEvent data is happening here
public class Event {
    private int eventID;
    private Time startTime;
    private Time endTime;
    private Room room;
    private String tagline;
    private String description;
    private User creators;
    private Timestamp creationTime;
    private boolean isCancelled;
    private boolean isPending;
    private boolean isRejected;
    private boolean check;
    private boolean isCourseEvent;
    private Course course;


    public Event(int eventID) {
        this.eventID = eventID;
    }

    public boolean addEvent(Time startTime, Time endTime, Room room, String tagline, String description, User creators, Timestamp creationTime, boolean isCancelled, boolean isPending, boolean isRejected, boolean check, boolean isCourseEvent, Course course) {
        return true;
    }

    public boolean deleteIt() {
        return true;
    }
}

