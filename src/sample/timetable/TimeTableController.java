package sample.timetable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TimeTableController extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Calendar");

        TimeTableGridPane gridPane = new TimeTableGridPane();
        Scene scene = new Scene(gridPane.getTimeTableGridPane(), 1200, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
