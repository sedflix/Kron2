package api;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Faculty extends User {

    @ManyToMany
    private List<Course> coursesTaught = new ArrayList<Course>();

    public Faculty(String name, String rollNumber, String email, String password, Group groupType) {
        super(name, rollNumber, email, password, groupType);
    }

    public Faculty() {
    }

    public boolean addEvent(Event event) {
        return true;
    }

    public boolean cancelEvent(Event event) {
        return true;
    }

    public boolean updateEvent(Event event) {
        return true;
    }

    public List<CourseEvent> getAllCourseEvent(){
        return new ArrayList<CourseEvent>();
    }

    public List<Event> getAllEvents() {
        return new ArrayList<Event>();
    }


}
