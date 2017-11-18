package sample.usercontrollers;

import api.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.courseview.CourseViewController;
import sample.createevent.CreateEvent;
import sample.requests.HandleRequestsController;
import sample.timetable.TimeTableController;
import sample.viewroom.ViewRoomController;

import java.io.IOException;

public class StudentPage extends Application{
    private User user;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Dashboard");
        Parent menu = FXMLLoader.load(getClass().getResource("/sample/usercontrollers/fxml/menu.fxml"));
        BorderPane borderPane = (BorderPane)menu.lookup("#borderPane");
        MenuBar menuBar =(MenuBar) borderPane.getTop();

        Button viewCourse = (Button) menuBar.getMenus().get(0).getGraphic();
        viewCourse.setOnAction(event -> {
            CourseViewController courseViewController = null;
            try {
                courseViewController = new CourseViewController();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        Button requestManagement = (Button) menuBar.getMenus().get(4).getGraphic();
        requestManagement.setOnAction(event -> {
            HandleRequestsController handleRequestsController = new HandleRequestsController();
            handleRequestsController.setUser(user);
            try {
                handleRequestsController.start(primaryStage);
                borderPane.setCenter(handleRequestsController.getRoot());
//                borderPane.getChildren().add(handleRequestsController.getRoot());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button createEvent = (Button) menuBar.getMenus().get(2).getGraphic();
        createEvent.setOnAction(event -> {
            System.out.println(234);
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
