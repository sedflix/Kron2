package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class CreateEvent extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/create_event.fxml"));

        ScrollPane scrollPane = (ScrollPane) root.lookup("#scroll_time_table");
        Node timeTable = FXMLLoader.load(getClass().getResource("/sample/TimeTable.fxml"));
        scrollPane.setContent(timeTable);
        primaryStage.setTitle("Kron2");

        primaryStage.setScene(new Scene(root, 900, 900));

        primaryStage.show();
    }

}
