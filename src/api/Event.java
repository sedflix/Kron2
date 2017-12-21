package api;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


//duplication of event data nd CourseEvent data is happening here
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventID;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Course course;

    @Column
    private String tagline;


    @Column
    private String description;

    @ManyToOne
    private User creators;

    @Basic
    private Timestamp creationTime;

    @Basic
    private Time startTime;

    @Basic
    private Time endTime;

    @Basic
    private Date date;

    @Column
    private boolean isCancelled;

    @Column
    private boolean isPending;
    @Column
    private boolean isRejected;
    @Column
    private boolean checkWhy;
    @Column
    private boolean isCourseEvent;


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


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isCheckWhy() {
        return checkWhy;
    }

    public void setCheckWhy(boolean checkWhy) {
        this.checkWhy = checkWhy;
    }

    public boolean isCourseEvent() {
        return isCourseEvent;
    }

    public void setCourseEvent(boolean courseEvent) {
        isCourseEvent = courseEvent;
    }
}

