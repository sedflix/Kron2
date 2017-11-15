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
            faltu(session);
            session.saveOrUpdate(prof);
            session.saveOrUpdate(course);
            session.getTransaction().commit();
        }
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
        Course course=new Course();
        List<String> code = new ArrayList(1);
        List<Integer> number = new ArrayList<>(1);
        Set<Faculty> faculty = new HashSet<>(1);
        List<Department> departments = new ArrayList<>(1);
        String postConditions;
        String preConditions;

        Faculty prof = new Faculty();
        prof.setName("Vivek Kumar");
        prof.setEmail("vivekk@iiitd.ac.in");
        prof.setPassword("vivekk");
        code.add("CSE201");
        number.add(201);
        faculty.add(prof);
        departments.add(Department.CSE);
        course.setCourseCode("AP");
        course.setName("Advanced Programming");
        postConditions="";
        postConditions+="Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.";
        postConditions+=" Implement basic event driven programming, exception handling, and threading.";
        postConditions+=" Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.";
        postConditions+=" Students are able to select and use a few key design pattern to solve a given problem in hand.";
        postConditions+=" Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.";
        preConditions="";
        preConditions+="CSE101";
        preConditions+="CSE102";
        course.setCredits(4);
        course.setFaculties(faculty);
        course.setCourseNumber(number);
        course.setDepartments(departments);
        course.setPostConditions(postConditions);
        course.setPreConditions(preConditions);
        cse.add(course);
        temp.setRegisteredCourse(cse);
        session.saveOrUpdate(prof);
        session.saveOrUpdate(course);
        session.saveOrUpdate(temp);

        Student temp1 = new Student();
        temp1.setName("Siddhartha Jain");
        temp1.setEmail("siddhartha16269@iiitd.ac.in");
        temp1.setRollNumber("2016269");
        temp1.setPassword("G-k%Q29Y");


        Set<Course> csam = new HashSet<>();
        Course course1=new Course();
        List<String> code1 = new ArrayList(1);
        List<Integer> number1 = new ArrayList<>(1);
        Set<Faculty> faculty1 = new HashSet<>(1);
        List<Department> departments1 = new ArrayList<>(1);
        String postConditions1;
        String preConditions1;

        Faculty prof1 = new Faculty();
        prof1.setName("Anuradha Sharma");
        prof1.setEmail("anu@iiitd.ac.in");
        prof1.setPassword("anuradha");
        code1.add("MTH270");
        number1.add(270);
        faculty1.add(prof1);
        departments1.add(Department.MTH);
        course1.setCourseCode("NT");
        course1.setName("Number Theory");
        postConditions1="";
        postConditions1+="Something about number Theory";
        postConditions1+=" Linear Congruences";
        postConditions1+=" certain non linear diophantine equations";
        preConditions1="";
        course1.setCredits(4);
        course1.setFaculties(faculty1);
        course1.setCourseNumber(number1);
        course1.setDepartments(departments1);
        course1.setPostConditions(postConditions1);
        course1.setPreConditions(preConditions1);
        temp1.setRegisteredCourse(csam);

        session.saveOrUpdate(prof1);
        session.saveOrUpdate(course1);
        session.saveOrUpdate(temp1);

        return;
    }
}
