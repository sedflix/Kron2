package sample.timetable;

import api.Student;
import api.User;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is controller to generate TimeTable. This also lets us view Course details when we click on time table cards.
 */

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
        gridPane.setUser(user);

        //TODO: edit for faculty and admin
        if (user.getDtype().equals("Student")) {
            gridPane.addAllCoureseOfStudent((Student) user);
        } else if (user.getDtype().equals("Faculty")) {
            gridPane.addAllCourses();
        } else if (user.getDtype().equals("Admin")) {
            gridPane.addAllCourses();
        }


//        Student student = session.get(Student.class, "siddharth16268@iiitd.ac.in");
//
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
