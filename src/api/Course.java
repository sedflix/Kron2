package api;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String name;
    private List<String> courseCode;
    private List<Department> departments;
    private List<Integer> courseNumber;
    private List<Faculty> faculties;
    private String postConditions;
    private List<Course> preqs;
    private int credits;
    private List<CourseEvent> event;


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
