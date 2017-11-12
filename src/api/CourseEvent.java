package api;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//all the stuff we get from course is present here

/**
 * The Fixed Weekly timetable will be taken from this table
 */
@Entity
public class CourseEvent {
    @Id
    @GeneratedValue
    private int courseEventID;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @ManyToOne
    private Room room;

    @Column
    private String description;

    @Column
    private DayOfWeek dayOfWeek;

    @ManyToOne
    private Course course;

    @Column
    private int eventType; // 1 -> Class; 2 -> Lab; 3 -> TA ;

    public CourseEvent(Time startTime, Time endTime, Room room, String description, DayOfWeek dayOfWeek, Course course, int eventType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.course = course;
        this.eventType = eventType;
    }

    public CourseEvent() {
    }

    public int getCourseEventID() {
        return courseEventID;
    }

    public void setCourseEventID(int courseEventID) {
        this.courseEventID = courseEventID;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

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

