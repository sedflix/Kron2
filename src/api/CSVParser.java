package api;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(String [] args) throws Exception{
        final Session session = getSession();


        try {
            Course course=new Course();
            List<String> code = new ArrayList(1);
            List<Integer> number = new ArrayList(1);
            List<Faculty> faculty = new ArrayList(1);
            List<Department> departments = new ArrayList(1);
            List<String> postConditions = new ArrayList(1);
            List<String> preConditions = new ArrayList(1);
            Faculty prof = new Faculty();
//            prof.setName("Dhongoon Chang");
//            prof.setEmail("dhongoon@iiitd.ac.in");
//            prof.setPassword("dhong");
//            code.add("CSE121");
//            number.add(121);
//            faculty.add(prof);
//            departments.add(Department.CSE);
//            course.setCourseCode(code);
//            course.setName("Discrete Mathematics");
//            postConditions.add("Be able to read, interpret and write some basic mathematical notation(s)");
//            postConditions.add("be able to recognize and/or construct examples of mathematical objects introduced during the course, such as the sets and functions");
//            postConditions.add("have been introduced to several mathematical models, (e.g. propositional logic, trees) including some of those underlying computing and information technology");
//            postConditions.add("have had the opportunity to develop capacity in knowing what constitutes a valid argument, and in constructing valid arguments/proofs");
//            postConditions.add("have had an opportunity to develop the problem-solving skills");
//            course.setCredits(4);
//            course.setFaculties(faculty);
//            course.setCourseNumber(number);
//            course.setDepartments(departments);
//            course.setPostConditions(postConditions);
//            course.setPreConditions(preConditions);
//            session.beginTransaction();
//            session.persist(course);
//            session.getTransaction().commit();


            prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();





prof.setName("Naveen Prakash");
            prof.setEmail("naveen@iiitd.ac.in");
            prof.setPassword("navpra");
            code.add("CSE112");
            number.add(112);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Computer Organization");
            postConditions.clear();
            postConditions.add("Write program in assembly language(RISC ISA) - Compare performance between several implementations of a computer program.");
            postConditions.add("Analyse processor performance for different implementation strategies : eg. single vs. multicycle,pipelined vs. non-pipelined execution.");
            postConditions.add("Simulate and compare performance of cache memory,compare caches with different configuration.");
            postConditions.add("Analyse basic I/O operation and their performance.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE111");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();





prof.setName("Sarthok Sircar");
            prof.setEmail("ssircar@iiitd.ac.in");
            prof.setPassword("sircar");
            code.add("MTH203");
            number.add(203);
            faculty.add(prof);
            departments.add(Department.MTH);
            course.setCourseCode(code);
            course.setName("Math-III");
            postConditions.clear();
            postConditions.add("Students are able to apply concepts of continuity, differentiability, extrema and integrability of multivariable functions and evaluate various integrals (line, double, triple and surface integrals).Students are able to work with vector fields, evaluate line\n" +
                    "and surface integrals, calculate quantities such as work,circulation and flux across plane curves and surfaces, be able to carry out vector derivative operations such as gradient, divergence and curl and understand and apply Green's, Stoke's and divergenceÂ Â theorems.Students are able to evaluate\n" +
                    "derivatives and integrals of complex functions, including the applications of Cauchyâ€™s theorem.Students are able to determine convergence of complex series and power series and understand and apply Taylor series to represent complex\n" +
                    "functions.");
            preConditions.clear();
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();




prof.setName("Vivek Kumar");
            prof.setEmail("vivekk@iiitd.ac.in");
            prof.setPassword("vivekk");
            code.add("CSE201");
            number.add(201);
            faculty.add(prof);
            departments.add(Department.CSE);
            course.setCourseCode(code);
            course.setName("Advanced Programming");
            postConditions.clear();
            postConditions.add("Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects), interfaces, polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code.");
            postConditions.add("Implement basic event driven programming, exception handling, and threading.");
            postConditions.add("Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML.");
            postConditions.add("Students are able to select and use a few key design pattern to solve a given problem in hand.");
            postConditions.add("Students are able to use common tools for testing (e.g., JUnit), debugging, and source code control as an integral part of program development.");
            preConditions.clear();
            preConditions.add("CSE101");
            preConditions.add("CSE102");
            course.setCredits(4);
            course.setFaculties(faculty);
            course.setCourseNumber(number);
            course.setDepartments(departments);
            course.setPostConditions(postConditions);
            course.setPreConditions(preConditions);
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();

        }
        finally {
            session.close();
            ourSessionFactory.close();
        }
    }
}
