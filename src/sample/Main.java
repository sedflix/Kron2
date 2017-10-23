package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Group root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Node x = FXMLLoader.load(getClass().getResource("/sample/MenuBar.fxml"));
        Node y = FXMLLoader.load(getClass().getResource("/sample/TimeTable.fxml"));

        root = new Group();
        root.getChildren().addAll(x, y);
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 6000, 500));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
