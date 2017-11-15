package api;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.*;

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
    private Set<Faculty> faculties = new HashSet<>();

    @ManyToMany(mappedBy = "registeredCourse")
    private Set<Student> registeredStudents = new HashSet<>();

    @ManyToMany(mappedBy = "auditedCourse")
    private Set<Student> auditedStudents = new HashSet<>();

    @ManyToMany(mappedBy = "shoppingCourse")
    private Set<Student> shoppingStudents = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<CourseEvent> courseEvents = new HashSet<>();

    public Course(String name, String courseCode, List<Department> departments, List<Integer> courseNumber, Set<Faculty> faculties, String postConditions, int credits) {
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


    /**
     * It will break the search term using the space as a delimiter.
     *
     * @param searchTerm string consisting of keywords
     * @return List of Course whose precondition contains *any of the word* in search term
     */
    public static List<Course> search(String searchTerm) {
        List<Course> listOfAllCourses = new ArrayList<>();
        if (searchTerm == null) {
            return listOfAllCourses;
        }
        String sp[] = searchTerm.split(" ");

        Arrays.stream(sp).parallel().forEach(str -> {
            listOfAllCourses.addAll(searchByKeyword(str));
        });

        return listOfAllCourses;
    }

    /**
     * @param searchTerm search for <b>exact</b> occurrence of searchTerm in postCondition
     * @return List of Course whose precondition contains <b>exactly</b> the search term
     */
    public static List<Course> searchByKeyword(String searchTerm) {

        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Course course where course.postConditions like :postCondition");
        query.setParameter("postCondition", "%" + searchTerm + "%");
        List<Course> list = query.getResultList();
        session.close();
        return list;

    }

    public static List<Course> getAllCourses() {
        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Course");
        List<Course> list = query.getResultList();
        session.close();
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
        Course.search("Major schools of poetry post 19th century");
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

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
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
