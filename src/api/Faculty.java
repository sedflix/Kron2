package api;

import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Faculty extends User {

    @ManyToMany(mappedBy = "faculties")
    private List<Course> coursesTaught = new ArrayList<Course>();

    /**
     * Default constructor
     */
    public Faculty() {
    }

    /**
     * Creates an COURSE Event with the given specification and sets
     * isPending = false
     * isRejected = false
     * isCancelled = false
     * <p>
     * Checks if time is sensible
     * checks if room is free between the given time
     *
     * @param name        Event name
     * @param room        Room Object
     * @param description Description of the event
     * @param startTime   start time of the event
     * @param endTime     end time of the event
     * @param date        event date
     * @param course      The course with which the event is related
     * @return true if event creation is successful
     */
    public boolean addEvent(String name, Room room, String description, Time startTime, Time endTime, Date date, Course course) {

        if (new java.util.Date().before(startTime)) {
            System.out.println("Make a time machine first");
            return false;
        }
        if (startTime.after(endTime)) {
            System.out.println("Make a time machine first");
            return false;
        }
        if (!room.isFreeBetween(startTime, endTime, date)) {
            System.out.println("Room not free");
            return false;
        }

        Event event = new Event();
        event.setTagline(name);
        event.setDescription(description);

        event.setRoom(room);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setDate(date);
        event.setCancelled(false);
        event.setRejected(false);
        event.setPending(false);
        event.setCourseEvent(course != null);
        event.setCourse(course);
        event.setCreationTime(new Timestamp(new java.util.Date().getTime()));
        event.setCreators(this);

        try {
            Session session = MySession.getSession();
            session.beginTransaction();
            session.saveOrUpdate(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Saving event failed");
            return false;
        }

        return true;
    }

    /**
     * Creates an Event with the given specification and sets
     * isPending = false
     * isRejected = false
     * isCancelled = false
     * course is set null
     *
     * Checks if time is sensible
     * checks if room is free between the given time
     *
     * @param name Event name
     * @param room Room Object
     * @param description Description of the event
     * @param startTime start time of the event
     * @param endTime end time of the event
     * @param date event date
     * @return true if event creation is successful
     */
    public boolean addEvent(String name, Room room, String description, Time startTime, Time endTime, Date date) {
        Session session = MySession.getSession();
        return addEvent(name, room, description, startTime, endTime, date, null);
    }

    /**
     * list of Event created by this
     * @return list of Event created by this
     */
    public List<Event> getAllRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event where event.creators = :me");
        query.setParameter("me", this);
        return (List<Event>) query.getResultList();
    }


}
