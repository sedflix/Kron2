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
import sample.createevent.CreateEvent;
import sample.requests.HandleRequestsController;
import sample.viewroom.ViewRoomController;
import sample.welcomepage.Controller;

public class AdminPage extends Application{
    private User user;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Dashboard");
        Parent menu = FXMLLoader.load(getClass().getResource("/sample/usercontrollers/fxml/menuAdmin.fxml"));
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
//                borderPane.getChildren().addAll(createEvent1.getRoot());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        Button requestManagement = (Button) menuBar.getMenus().get(2).getGraphic();
        requestManagement.setOnAction(event -> {
            HandleRequestsController handleRequestsController = new HandleRequestsController();
            handleRequestsController.setUser(user);
            try {
                handleRequestsController.start(primaryStage);
                borderPane.setCenter(handleRequestsController.getRoot());
                borderPane.getChildren().add(handleRequestsController.getRoot());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        Button logout = (Button) menuBar.getMenus().get(3).getGraphic();
        logout.setOnAction(event -> {
            Controller controller = new Controller();
            try{
                controller.start(primaryStage);
            }
            catch (Exception e){
                e.printStackTrace();
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
