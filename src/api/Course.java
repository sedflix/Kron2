package api;

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

    @ElementCollection
    @CollectionTable
    @Column(length = 10000)
    private List<String> postConditions;

    @ElementCollection
    @CollectionTable
    @Column(length = 10000)
    private List<String> preConditions;

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

    public Course(String name, String courseCode, List<Department> departments, List<Integer> courseNumber, List<Faculty> faculties, List<String> postConditions, int credits) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPreConditions() {
        return preConditions;
    }

    public void setPreConditions(List<String> preConditions) {
        this.preConditions = preConditions;
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

    public List<String> getPostConditions() {
        return postConditions;
    }

    public void setPostConditions(List<String> postConditions) {
        this.postConditions = postConditions;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    public List<Course> search(String query) {
        return new ArrayList<Course>();
    }

    public List<CourseEvent> getCourseEvents() {
        return new ArrayList<CourseEvent>();
    }

    public List<Event> getThisWeekEvents() {
        return new ArrayList<Event>();
    }
}
