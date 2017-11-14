package api;

import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(length = 50000)
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
    private List<CourseEvent> events = new ArrayList<>();

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

        Criteria crit = session.createCriteria(Course.class);
//        Restrictions.
//        List<Course> results = crit.list();
//        results.forEach((Course o) ->{
//            System.out.println(o.getCourseCode());
//        });


        return new ArrayList<Course>();
    }

    public static void main(String[] args) {
        Session session = CSVParser.getSession();
        Course.search(session, "CSE");
        session.close();
    }

    public List<CourseEvent> getCourseEvents() {
        return new ArrayList<CourseEvent>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getThisWeekEvents() {
        return new ArrayList<Event>();
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
