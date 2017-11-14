package api;

import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin extends Faculty {

    public Admin() {
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
