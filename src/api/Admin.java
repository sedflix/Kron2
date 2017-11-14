package api;

import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public boolean addEvent(Session session, String name, Room room, String description, Time startTime, Time endTime, Date date) {

        if (new java.util.Date().before(startTime)) {
            System.out.println("Make a time machine first");
            return false;
        }

        if (startTime.after(endTime)) {
            System.out.println("Make a time machine first");
            return false;
        }
        if (!room.isFreeBetween(session, startTime, endTime, date)) {
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
        event.setCourseEvent(false);
        event.setCreationTime(new Timestamp(new java.util.Date().getTime()));
        event.setCreators(this);

        try {
            session.beginTransaction();
            session.saveOrUpdate(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Saving event failed due to " + e.getMessage());
            return false;
        }

        return true;
    }

    public List<Event> getAllRequests() {
        return new ArrayList<Event>();
    }


    public List<Event> getAllPendingRequests(Session session) {
        Query query = session.createQuery("select event from Event as event where event.isPending = true");
        return (List<Event>) query.getResultList();
    }

    public List<Event> getAllRejectdRequests(Session session) {
        Query query = session.createQuery("select event from Event as event where event.isRejected = true");
        return (List<Event>) query.getResultList();
    }


    public boolean approveEventRequest(Session session, Event event) {

        if (!event.getRoom().isFreeBetween(session, event.getStartTime(), event.getEndTime(), event.getDate())) {
            System.out.println("Room is occupied");
            return false;
        }

        try {
            session.beginTransaction();
            event.setPending(false);
            session.update(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cannot approve: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean rejectEventRequest(Session session, Event event) {

        try {
            session.beginTransaction();
            event.setPending(false);
            event.setRejected(true);
            session.update(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cannot reject this request: " + e.getMessage());
            return false;
        }
        return true;
    }


    public boolean cancelEvent(Session session, Event event) {
        try {
            session.beginTransaction();
            event.setPending(false);
            event.setCancelled(true);
            session.update(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cannot cancel this event: " + e.getMessage());
            return false;
        }
        return true;
    }

}
