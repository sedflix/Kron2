package sample;

import api.Admin;
import api.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.timetable.TimeTableController;

public class AdminPage extends Application{
    private User user;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Dashboard");
        Parent menu = FXMLLoader.load(getClass().getResource("/sample/menuAdmin.fxml"));
        BorderPane borderPane = (BorderPane)menu.lookup("#borderPane");
        MenuBar menuBar =(MenuBar) borderPane.getTop();


        Button viewRoom = (Button) menuBar.getMenus().get(1).getGraphic();
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


        Button createEvent = (Button) menuBar.getMenus().get(0).getGraphic();
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
