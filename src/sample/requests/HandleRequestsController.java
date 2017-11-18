package sample.requests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HandleRequestsController extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Requests");

        HandleRequestGridPane gridPane = new HandleRequestGridPane();
        Scene scene = new Scene(gridPane.getGridPanel(), 1200, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
