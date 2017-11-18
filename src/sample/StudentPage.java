package sample;

import api.Course;
import api.Student;
import api.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.timetable.TimeTableController;

import java.beans.EventHandler;
import java.util.concurrent.ExecutionException;

public class StudentPage extends Application{
    private User user;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Dashboard");
        Parent menu = FXMLLoader.load(getClass().getResource("/sample/menu.fxml"));
        BorderPane borderPane = (BorderPane)menu.lookup("#borderPane");
        MenuBar menuBar =(MenuBar) borderPane.getTop();

        Button viewCourse = (Button) menuBar.getMenus().get(0).getGraphic();
        viewCourse.setOnAction(event -> {
            CourseViewController courseViewController = new CourseViewController();
            courseViewController.setUser(user);
//            System.out.println("34");
            try{
                courseViewController.start(primaryStage);
                borderPane.setCenter(courseViewController.getCourseDescription());
                borderPane.getChildren().addAll(courseViewController.getCourseDescription());
            }
            catch (Exception e){

            }
        });

        Button viewRoom = (Button) menuBar.getMenus().get(3).getGraphic();
        viewRoom.setOnAction(event -> {
            ViewRoomController viewRoomController = new ViewRoomController();
            viewRoomController.setUser(user);
            try{
                viewRoomController.start(primaryStage);
                borderPane.setCenter(viewRoomController.getRoot());
                borderPane.getChildren().addAll(viewRoomController.getRoot());
            }
            catch (Exception e){

            }
        });

        Button viewTimetable = (Button) menuBar.getMenus().get(1).getGraphic();
        viewTimetable.setOnAction(event -> {
            TimeTableController timeTableController = new TimeTableController();
            timeTableController.setUser(user);
            try{
                timeTableController.start(primaryStage);
                borderPane.setCenter(timeTableController.getGridPane().getGridPane());
                borderPane.getChildren().addAll(timeTableController.getGridPane().getGridPane());
            }
            catch (Exception e){

            }
        });

        Button createEvent = (Button) menuBar.getMenus().get(2).getGraphic();
        createEvent.setOnAction(event -> {
            CreateEvent createEvent1 = new CreateEvent();
            createEvent1.setUser(user);
            try{
                createEvent1.start(primaryStage);
                borderPane.setCenter(createEvent1.getRoot());
                borderPane.getChildren().addAll(createEvent1.getRoot());
            }
            catch (Exception e){

            }
        });


//        borderPane.setCenter();
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();

    }
    public static void main(String args[]){
        launch(args);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
