package api;

import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public class WeeklyCron {
    public static void main(String[] args) {
        Session session = MySession.getSession();

        List<Course> courses = Course.getAllCourses();
        courses.forEach(course -> {
            Set<CourseEvent> courseEvents = course.getCourseEvents();
            courseEvents.forEach(courseEvent -> {

                Event event1 = new Event();
                event1.setCourse(course);
                event1.setCourseEvent(true);
                event1.setRoom(courseEvent.getRoom());
                event1.setTagline(courseEvent.getCourse().getName());
                String str = "";
                if (courseEvent.getEventType() == 1) {
                    str = "Lecture";
                } else if (courseEvent.getEventType() == 2) {
                    str = "Lab";
                } else if (courseEvent.getEventType() == 3) {
                    str = "Tut";
                }
                event1.setDescription(str);
                event1.setCreators((Faculty) course.getFaculties().toArray()[0]);
                event1.setStartTime(courseEvent.getStartTime());
                event1.setEndTime(courseEvent.getEndTime());
//                Date date = new Date(System.currentTimeMillis());
//
//                event1.setDate();
//                int x = (Calendar.getInstance().getFirstDayOfWeek()-  courseEvent.getDayOfWeek().getValue());
                event1.setPending(false);
                event1.setCancelled(false);
                event1.setRejected(false);
                event1.setCheckWhy(false);
                event1.setCourseEvent(true);
            });
        });
    }
}
