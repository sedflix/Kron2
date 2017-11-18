package api;

import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.util.List;

@Entity
public class Admin extends Faculty {

    public Admin() {
    }


    public List<Event> getAllRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event");
        return (List<Event>) query.getResultList();
    }


    public List<Event> getAllPendingRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event where event.isPending = true");
        return (List<Event>) query.getResultList();
    }

    public List<Event> getAllRejectdRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event where event.isRejected = true");
        return (List<Event>) query.getResultList();
    }


    public boolean approveEventRequest(Event event) {
        Session session = MySession.getSession();
        if (!event.getRoom().isFreeBetween(event.getStartTime(), event.getEndTime(), event.getDate())) {
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

    public boolean rejectEventRequest(Event event) {
        Session session = MySession.getSession();
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


    public boolean cancelEvent(Event event) {
        Session session = MySession.getSession();
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
