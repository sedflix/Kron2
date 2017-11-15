package api;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Course {


    @Column
    private String name;

    @Column
    @Id
    private String courseCode;

    @ElementCollection
    @CollectionTable
    @Column
    private List<Department> departments;

    @ElementCollection
    @CollectionTable
    @Column
    private List<Integer> courseNumber;

    @Column(length = 10000)
    private String postConditions;


    @Column(length = 10000)
    private String preConditions;

    @Column
    private int credits;

    @ManyToMany(mappedBy = "coursesTaught")
    private List<Faculty> faculties = new ArrayList<>();

    @ManyToMany(mappedBy = "registeredCourse")
    private List<Student> registeredStudents = new ArrayList<>();

    @ManyToMany(mappedBy = "auditedCourse")
    private List<Student> auditedStudents = new ArrayList<>();

    @ManyToMany(mappedBy = "shoppingCourse")
    private List<Student> shoppingStudents = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseEvent> courseEvents = new ArrayList<>();

    public Course(String name, String courseCode, List<Department> departments, List<Integer> courseNumber, List<Faculty> faculties, String postConditions, int credits) {
        this.name = name;
        this.courseCode = courseCode;
        this.departments = departments;
        this.courseNumber = courseNumber;
        this.faculties = faculties;
        this.postConditions = postConditions;
        this.credits = credits;
    }

    public Course() {
    }


    public static List<Course> search(Session session, String searchTerm) {

        Query query = session.createQuery("FROM Course course where course.postConditions like :postCondition");
        query.setParameter("postCondition", "%" + searchTerm + "%");
        List<Course> list = query.getResultList();
        return list;

    }

    public List<Event> getThisWeekEvents(Session session) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.getWeekYear(), calendar.getWeeksInWeekYear(), 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar.getWeekYear(), calendar.getWeeksInWeekYear(), 6);
        return getEventInTimeSpan(session, calendar.getTime(), calendar2.getTime());
    }

    public List<Event> getEventInTimeSpan(Session session, Date start, Date end) {
        Query query = session.createQuery("FROM Event event where event.course = :nocourse and ( event.date >= :startDate and event.date <= :endDate )", Event.class);
        query.setParameter("nocourse", this);
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        List<Event> list = query.getResultList();
        return list;
    }

    public static void main(String[] args) {
        Session session = MySession.getSession();
        Course.search(session, "Major schools of poetry post 19th century");
        session.close();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPreConditions() {
        return preConditions;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Integer> getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(List<Integer> courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public void setPreConditions(String preConditions) {
        this.preConditions = preConditions;
    }

    public String getPostConditions() {
        return postConditions;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setPostConditions(String postConditions) {
        this.postConditions = postConditions;
    }
}
