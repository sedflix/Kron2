package api;

import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@Entity
public class Admin extends Faculty {

    /**
     * My lovely default constructor
     */
    public Admin() {
    }


    /**
     * Only admin function they have everything. EVERYTHING.
     * Gives all the events in the database
     *
     * @return List of all the Events in the database
     */
    public List<Event> getAllRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event");
        return (List<Event>) query.getResultList();
    }


    /**
     * Gives all the pending events in the database
     * @return List of all the Events that are pending in the database
     */
    public List<Event> getAllPendingRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event where event.isPending = true");
        return (List<Event>) query.getResultList();
    }

    /**
     * Gives all the rejected events in the database
     * @return List of all the Events that are pending in the database
     */
    public List<Event> getAllRejectdRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event where event.isRejected = true");
        return (List<Event>) query.getResultList();
    }


    /**
     * Calls this when you want to approve an event.
     * @return returns the true if the event is approved successfully
     */
    public boolean approveEventRequest(Event event) {
        Session session = MySession.getSession();
        if (!event.getRoom().isFreeBetween(event.getStartTime(), event.getEndTime(), event.getDate())) {
            System.out.println("Room is occupied");
            return false;
        }

        try {
            session.beginTransaction();
            event.setPending(false);
            event.setRejected(false);
            event.setCancelled(false);
            session.update(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cannot approve: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Calls this when you want to reject an event.
     * @return returns the true if the event is rejected successfully
     */
    public boolean rejectEventRequest(Event event) {
        Session session = MySession.getSession();
        try {
            session.beginTransaction();
            event.setPending(false);
            event.setRejected(true);
            event.setCancelled(false);
            session.update(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cannot reject this request: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Calls this when you want to cancel an event.
     * @return returns the true if the event is cancelled successfully
     */

    public boolean cancelEvent(Event event) {
        Session session = MySession.getSession();
        try {
            session.beginTransaction();
            event.setPending(false);
            event.setCancelled(true);
            event.setRejected(false);
            session.update(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cannot cancel this event: " + e.getMessage());
            return false;
        }
        return true;
    }

}
