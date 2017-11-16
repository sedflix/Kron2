package api;

import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVParser {

    public static void main(String[] args) throws Exception {


        final Session session = MySession.getSession();


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

//            System.out.println(line);


            //initialisation of required objects
            Course course = new Course();
            Set<Integer> number = new HashSet<>(1);
            Set<Faculty> faculty = new HashSet<>();
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
            course.setCourseCode(((String) b[5]).trim());

            for (int i = 0; i<5; i++) {
                String timeRoomInfo = ((String) b[6+i]).replaceAll(" ","");
                if (timeRoomInfo.length()<10){
                    continue;
                }
                Time startTime = new Time(Integer.parseInt(timeRoomInfo.substring(0, 2)), Integer.parseInt(timeRoomInfo.substring(3, 5)), 00);
                Time endTime = new Time(Integer.parseInt(timeRoomInfo.substring(6, 8)), Integer.parseInt(timeRoomInfo.substring(9, 11)), 00);
                Room room = new Room(timeRoomInfo.substring(12));
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
                Event event1 = new Event();
                event1.setCourse(course);
                event1.setCourseEvent(true);
                event1.setRoom(room);
                event1.setTagline("Class aa jaya karo bc");
                event1.setDescription("useless as fuck");
                event1.setCreators((User) prof);
                event1.setStartTime(startTime);
                event1.setEndTime(endTime);
                event1.setDate(new Date(startTime.getTime()));
                event1.setPending(true);
                event1.setCancelled(false);
                event1.setRejected(false);
                event1.setCheckWhy(false);

                session.saveOrUpdate(event1);
                try {
                    session.saveOrUpdate(room);
                } catch (Exception e) {

                }
                session.saveOrUpdate(event);
            }

            // Tutorial Information
            extraInformation(session, b[11], course,3);
            extraInformation(session, b[12], course,2);
            session.saveOrUpdate(prof);
            session.saveOrUpdate(course);
            session.getTransaction().commit();
        }
        faltu(session);
        br.close();
        session.close();
        MySession.closeSessionFactory();


    }

    private static void extraInformation(Session session, Object o, Course course, int type) {
        String tutDayTimeInfo = ((String) o).replaceAll(" ","");
//        System.out.println(tutDayTimeInfo);
        while (tutDayTimeInfo.length()>1){
//                System.out.println("whatthefuck");

            int tempFirstDollarIndex = tutDayTimeInfo.indexOf("$");
            int tempSecondDollarIndex = tutDayTimeInfo.indexOf("$", tempFirstDollarIndex+1);
            int tempHashIndex = tutDayTimeInfo.indexOf("#",tempSecondDollarIndex+1);

            String day = tutDayTimeInfo.substring(0,tempFirstDollarIndex);
            String timeInfo = tutDayTimeInfo.substring(tempFirstDollarIndex+1,tempSecondDollarIndex);
            String roomInfo = tutDayTimeInfo.substring(tempSecondDollarIndex+1,tempHashIndex);

            Time startTime = new Time(Integer.parseInt(timeInfo.substring(0,2)),Integer.parseInt(timeInfo.substring(3,5)),00);
            Time endTime = new Time(Integer.parseInt(timeInfo.substring(6,8)),Integer.parseInt(timeInfo.substring(9,11)),00);

            DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
            if (day.equals("Monday")){
                dayOfWeek=DayOfWeek.MONDAY;
            }
            if (day.equals("Tuesday")){
                dayOfWeek=DayOfWeek.TUESDAY;
            }
            if (day.equals("Wednesday")){
                dayOfWeek=DayOfWeek.WEDNESDAY;
            }
            if (day.equals("Thursday")){
                dayOfWeek=DayOfWeek.THURSDAY;
            }
            if (day.equals("Friday")){
                dayOfWeek=DayOfWeek.FRIDAY;
            }

            while (roomInfo.length()>2){
                int semi = roomInfo.indexOf(";");
                if (semi<0){
                    break;
                }
                String tempRoom = roomInfo.substring(0,semi);
                Room room = new Room(tempRoom);
                CourseEvent event = new CourseEvent(startTime,endTime,room,"description", dayOfWeek,course, type);
                roomInfo = roomInfo.substring(semi+1);

                try {
                    session.saveOrUpdate(room);
                } catch (Exception e) {

                }
                session.saveOrUpdate(event);
            }

            tutDayTimeInfo = tutDayTimeInfo.substring(tempHashIndex+1);
        }
    }

    public static void faltu(Session session){

        Student temp = new Student();
        temp.setName("Siddharth Yadav");
        temp.setEmail("siddharth16268@iiitd.ac.in");
        temp.setRollNumber("2016268");
        temp.setPassword("[{Sid@123}]");

        Set<Course> cse = new HashSet<>();
        cse.add(Course.getCourseByName("Advanced Programming"));
        cse.add(Course.getCourseByName("Discrete Mathematics"));

        temp.setRegisteredCourse(cse);

        session.saveOrUpdate(temp);

        Student temp1 = new Student();
        temp1.setName("Siddhartha Jain");
        temp1.setEmail("siddhartha16269@iiitd.ac.in");
        temp1.setRollNumber("2016269");
        temp1.setPassword("g-Y87^k)");

        Set<Course> csam = new HashSet<>();
        csam.add(Course.getCourseByName("Number Theory"));
        csam.add(Course.getCourseByName("Introduction to Psychology"));

        temp1.setRegisteredCourse(csam);

        session.saveOrUpdate(temp1);

        Admin one = new Admin();
        one.setName("Ravi Bhasin");
        one.setEmail("ravi@iiitd.ac.in");
        one.setPassword("RaVi");

        session.saveOrUpdate(one);

        return;
    }
}
