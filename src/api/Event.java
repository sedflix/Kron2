package api;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//duplication of event data nd CourseEvent data is happening here
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventID;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;

    @ManyToOne
    private Room room;

    @Column
    private String tagline;

    @Column
    private String description;

    @ManyToOne
    private User creators;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creationTime;

    @Column
    private boolean isCancelled;

    @Column
    private boolean isPending;

    @Column
    private boolean isRejected;

    @Column
    private boolean check;

    @Column
    private boolean isCourseEvent;

    @ManyToOne
    private Course course;


    public Event(int eventID) {
        this.eventID = eventID;
    }

    public Event() {
    }



    public boolean addEvent(Time startTime, Time endTime, Room room, String tagline, String description, User creators, Timestamp creationTime, boolean isCancelled, boolean isPending, boolean isRejected, boolean check, boolean isCourseEvent, Course course) {
        return true;
    }

    public boolean deleteIt() {
        return true;
    }

    public boolean isRequestValid(Event event) {
        return true;
    }

    public List<Event> deleteInvalidRequests() {
        return new ArrayList<Event>();
    }

    public List<Event> getEventsOn() {
        return new ArrayList<Event>();
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreators() {
        return creators;
    }

    public void setCreators(User creators) {
        this.creators = creators;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCourseEvent() {
        return isCourseEvent;
    }

    public void setCourseEvent(boolean courseEvent) {
        isCourseEvent = courseEvent;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

