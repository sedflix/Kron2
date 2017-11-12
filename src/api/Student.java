package api;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Student extends User {


    @ManyToMany
    private List<Course> auditedCourse = new ArrayList<>();

    @ManyToMany
    private List<Course> registeredCourse = new ArrayList<>();
    ;

    @ManyToMany
    private List<Course> shoppingCourse = new ArrayList<>();


    public Student(String name, String rollNumber, String email, String password, Group groupType) {
        super(name, rollNumber, email, password, groupType);
    }

    public Student() {
    }

    public List<Course> getRegisterdCourses() {
        return new ArrayList<Course>();
    }

    public List<Course> getAuditCourses() {
        return new ArrayList<Course>();
    }

    public List<Course> getShoppingCoures() {
        return new ArrayList<Course>();
    }

    public List<Course> getAllCourses() {
        return new ArrayList<Course>();
    }

    public boolean registerCourse(Course course) {
        return true;
    }

    public boolean auditCourse(Course course) {
        return true;
    }

    public boolean addShoppingCourse(Course course) {
        return true;
    }

    public boolean createEventRequest(Event event) {
        return true;
    }

    public boolean requestEvent(Event event) {
        return true;
    }

    public List<Event> getAllEventRequests() {
        return new ArrayList<Event>();
    }

    public List<Event> getPendingEventRequests() {
        return new ArrayList<Event>();
    }

    public boolean cancelEvenntRequest(Event event) {
        return true;
    }
}
