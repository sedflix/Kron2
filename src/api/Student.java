package api;

import org.hibernate.Session;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student extends User {


    @ManyToMany(mappedBy = "auditedStudents")
    private Set<Course> auditedCourse = new HashSet<>();

    @ManyToMany(mappedBy = "registeredStudents", cascade = CascadeType.PERSIST)
    private Set<Course> registeredCourse = new HashSet<>();


    @ManyToMany(mappedBy = "shoppingStudents")
    private Set<Course> shoppingCourse = new HashSet<>();

    public Student() {
    }




    public List<Course> getAllCourses() {
        List<Course> allCourses = new ArrayList<>();
        allCourses.addAll(getRegisteredCourse());
        allCourses.addAll(getAuditedCourse());
        allCourses.addAll(getShoppingCourse());
        return allCourses;
    }


    /**
     * Give a course, it adds as a registered course
     *
     * @param course course that needs to be added
     * @return true if successful
     */
    public boolean insertRegisteredCourse(Course course) {

        Session session = MySession.getSession();

        session.beginTransaction();

        this.getRegisteredCourse().add(course);
        course.getRegisteredStudents().add(this);
        session.saveOrUpdate(this);
        session.saveOrUpdate(course);
        session.getTransaction().commit();

        return true;
    }

    /**
     * Give a course, it adds as a registered course
     *
     * @param course course that needs to be added
     * @return true if successful
     */
    public boolean insertShoppingCourse(Course course) {

        Session session = MySession.getSession();

        session.beginTransaction();

        this.getShoppingCourse().add(course);
        course.getShoppingStudents().add(this);
        session.saveOrUpdate(this);
        session.saveOrUpdate(course);
        session.getTransaction().commit();

        return true;
    }

    /**
     * Give a course, it adds as a registered course
     *
     * @param course course that needs to be added
     * @return true if successful
     */
    public boolean insertAuditedCourse(Course course) {

        Session session = MySession.getSession();

        session.beginTransaction();

        this.getAuditedCourse().add(course);
        course.getAuditedStudents().add(this);
        session.saveOrUpdate(this);
        session.saveOrUpdate(course);
        session.getTransaction().commit();

        return true;
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


    public Set<Course> getAuditedCourse() {
        return auditedCourse;
    }

    public void setAuditedCourse(Set auditedCourse) {
        this.auditedCourse = auditedCourse;
    }

    public Set<Course> getRegisteredCourse() {
        return registeredCourse;
    }

    public void setRegisteredCourse(Set<Course> registeredCourse) {
        this.registeredCourse = registeredCourse;
    }

    public Set<Course> getShoppingCourse() {
        return shoppingCourse;
    }

    public void setShoppingCourse(Set<Course> shoppingCourse) {
        this.shoppingCourse = shoppingCourse;
    }
}
