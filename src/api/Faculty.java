package api;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User {


    public Faculty(String name, String rollNumber, String email, String password, Group groupType) {
        super(name, rollNumber, email, password, groupType);
    }

    public boolean addEvent(Event event) {
        return true;
    }

    public boolean cancelEvent(Event event) {
        return true;
    }

    public List<CourseEvent> getAllCourseEvent(){
        return new ArrayList<CourseEvent>();
    }

    public List<Event> getAllEvents() {
        return new ArrayList<Event>();
    }


}
