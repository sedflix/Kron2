package sample.timetable;

import api.MySession;
import api.Student;
import api.User;
import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;


public class TimeTableController extends Application {
    private User user;

    public static void main(String[] args) {
        Application.launch(args);
    }
    TimeTableGridPane gridPane;
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Kron2 : Calendar");

        gridPane = new TimeTableGridPane();
        Session session = MySession.getSession();

        //TODO: Remove this line
        //TODO: Integrate it with Student, Faculty and Admin
        Student student = session.get(Student.class, "siddharth16268@iiitd.ac.in");
        gridPane.addAllCoureseOfStudent(student);
//        Scene scene = new Scene(gridPane.getGridPane(), 1200, 480);
//        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public TimeTableGridPane getGridPane(){
        return gridPane;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
