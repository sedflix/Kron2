package api;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    CourseStudentMap courseStudentMap;

    public Student(String name, String rollNumber, String email, String password, Group groupType, CourseStudentMap courseStudentMap) {
        super(name, rollNumber, email, password, groupType);
        this.courseStudentMap = courseStudentMap;
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
