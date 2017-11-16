package api;

import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculty extends User {

    @ManyToMany(mappedBy = "faculties")
    private List<Course> coursesTaught = new ArrayList<Course>();

    public Faculty() {
    }

    public boolean addEvent(String name, Room room, String description, Time startTime, Time endTime, Date date, Course course) {

        Session session = MySession.getSession();
        if (new java.util.Date().before(startTime)) {
            System.out.println("Make a time machine first");
            return false;
        }
        if (startTime.after(endTime)) {
            System.out.println("Make a time machine first");
            return false;
        }
        if (!room.isFreeBetween(session, startTime, endTime, date)) {
            System.out.println("Room not free");
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
        event.setPending(false);
        event.setCourseEvent(course != null);
        event.setCourse(course);
        event.setCreationTime(new Timestamp(new java.util.Date().getTime()));
        event.setCreators(this);

        try {
            session.beginTransaction();
            session.saveOrUpdate(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Saving event failed");
            return false;
        }

        return true;
    }

    public boolean addEvent(String name, Room room, String description, Time startTime, Time endTime, Date date) {
        Session session = MySession.getSession();
        return addEvent(name, room, description, startTime, endTime, date, null);
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
