package api;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(String[] args) throws Exception {


        final Session session = getSession();


        BufferedReader br = new BufferedReader(new FileReader("cse2ndyear.csv"));
        String line;
        br.readLine(); //to ignore the heading

        while ((line = br.readLine()) != null) {

            session.beginTransaction();

            //breaking line into stuffs
            Object[] b = line.split(",");
            String name = (String) b[1];
            String email = name + "@iiitd.ac.in";
            int credits = Integer.parseInt(((String) b[4]).replaceAll(" ", ""));
            String tempCourseStream = ((String) b[2]).substring(0, 3);

            System.out.println(line);


            //initialisation of required objects
            Course course = new Course();
            List<Integer> number = new ArrayList<>(1);
            List<Faculty> faculty = new ArrayList<>(1);
            List<Department> departments = new ArrayList<>(1);
            Faculty prof = new Faculty();


            number.add(Integer.parseInt(((String) b[2]).substring(4)));
            prof.setName((String) b[3]);
            prof.setEmail(email);

            faculty.add(prof);

            if (tempCourseStream.equals("CSE")) {
                departments.add(Department.CSE);
            } else if (tempCourseStream.equals("HSS")) {
                departments.add(Department.HSS);
            } else if (tempCourseStream.equals("MTH")) {
                departments.add(Department.MTH);
            }



            course.setCredits(credits);
            course.setPreConditions((String) b[13]);
            course.setPostConditions((String) b[14]);
            course.setFaculties(faculty);
            course.setName(name);
            course.setDepartments(departments);
            course.setCourseCode((String) b[5]);

            for (int i = 0; i<5; i++) {
                String timeRoomInfo = ((String) b[6+i]).replaceAll(" ","");
                if (timeRoomInfo.length()<10){
                    continue;
                }
                Time startTime = new Time(Integer.parseInt(timeRoomInfo.substring(0, 2)), Integer.parseInt(timeRoomInfo.substring(3, 5)), 00);
                Time endTime = new Time(Integer.parseInt(timeRoomInfo.substring(6, 8)), Integer.parseInt(timeRoomInfo.substring(9, 11)), 00);
                Room room = new Room(timeRoomInfo.substring(13));
                DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
                if (i==0){
                    dayOfWeek=DayOfWeek.MONDAY;
                }
                else if(i==1){
                    dayOfWeek = DayOfWeek.TUESDAY;
                }
                else if(i==2){
                    dayOfWeek = DayOfWeek.WEDNESDAY;
                }
                else if (i==3){
                    dayOfWeek = DayOfWeek.THURSDAY;
                }
                else if (i==4){
                    dayOfWeek = DayOfWeek.FRIDAY;
                }
                CourseEvent event = new CourseEvent(startTime, endTime, room, "Why the fuck do we have description?", dayOfWeek, course, 1);
                session.saveOrUpdate(room);
                session.saveOrUpdate(event);
            }
            session.saveOrUpdate(prof);
            session.saveOrUpdate(course);
            session.getTransaction().commit();
        }
        br.close();
        session.close();
        ourSessionFactory.close();


    }
}
