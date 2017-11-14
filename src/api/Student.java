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
public class Student extends User {


    @ManyToMany
    private List<Course> auditedCourse = new ArrayList<>();

    @ManyToMany
    private List<Course> registeredCourse = new ArrayList<>();

    @ManyToMany
    private List<Course> shoppingCourse = new ArrayList<>();

    public Student() {
    }




    public List<Course> getAllCourses() {
        List<Course> allCourses = new ArrayList<>();
        allCourses.addAll(getRegisteredCourse());
        allCourses.addAll(getAuditedCourse());
        allCourses.addAll(getShoppingCourse());
        return allCourses;
    }

    public boolean hasRegisteredForCourse(Course course) {
        return getRegisteredCourse().contains(course);
    }

    public boolean hasAuditedCourse(Course course) {
        return getAuditedCourse().contains(course);
    }

    public boolean hasShoppedForCourse(Course course) {
        return getShoppingCourse().contains(course);
    }

    public boolean createEventRequest(Session session, String name, Room room, String description, Time startTime, Time endTime, Date date) {

        if (new java.util.Date().before(startTime)) {
            System.out.println("Make a time machine first");
            return false;
        }
        if (startTime.after(endTime)) {
            System.out.println("Make a time machine first");
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
        event.setPending(true);
        event.setCourseEvent(false);
        event.setCreationTime(new Timestamp(new java.util.Date().getTime()));
        event.setCreators(this);

        try {
            session.beginTransaction();
            session.saveOrUpdate(event);
            session.getTransaction().commit();

        } catch (Exception e) {

            System.out.println("Saving event failed because" + e.getMessage());
            return false;
        }

        return true;

    }

    public List<Event> getAllEventRequests(Session session) {

        Query query = session.createQuery("FROM Event event where event.creators = :user");
        query.setParameter("user", this);
        return (List<Event>) query.getResultList();
    }

    public List<Event> getPendingEventRequests(Session session) {
        Query query = session.createQuery("FROM Event event where event.creators = :user and event.isPending = :pending");
        query.setParameter("user", this);
        query.setParameter("pending", true);
        return (List<Event>) query.getResultList();
    }


    public List<Course> getAuditedCourse() {
        return auditedCourse;
    }

    public void setAuditedCourse(List<Course> auditedCourse) {
        this.auditedCourse = auditedCourse;
    }

    public List<Course> getRegisteredCourse() {
        return registeredCourse;
    }

    public void setRegisteredCourse(List<Course> registeredCourse) {
        this.registeredCourse = registeredCourse;
    }

    public List<Course> getShoppingCourse() {
        return shoppingCourse;
    }

    public void setShoppingCourse(List<Course> shoppingCourse) {
        this.shoppingCourse = shoppingCourse;
    }
}
