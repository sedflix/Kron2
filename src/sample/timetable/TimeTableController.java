package sample.timetable;

import api.MySession;
import api.Student;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;


public class TimeTableController extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Calendar");

        TimeTableGridPane gridPane = new TimeTableGridPane();
        Session session = MySession.getSession();
        Student student = session.get(Student.class, "siddharth16268@iiitd.ac.in");
        gridPane.addAllCoureseOfStudent(student);
        Scene scene = new Scene(gridPane.getGridPane(), 1200, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
