package api;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//all the stuff we get from course is present here

/**
 * The Fixed Weekly timetable will be taken from this table
 */
public class CourseEvent {
    private int courseEventID;
    private Time startTime;
    private Time endTime;
    private Room room;
    private String description;
    private DayOfWeek dayOfWeek;
    private Course course;
    private int eventType; // 1 -> Class; 2 -> Lab; 3 -> TA ;


    public boolean add() {
        return true;
    }

    public boolean update() {
        return true;
    }

    public List<Event> getAllCourseEvents() {
        return new ArrayList<Event>();
    }

    public List<Event> getCourseOn(Date date) {
        return new ArrayList<Event>();
    }


}

