package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController extends Application{
   @Override
    public void start(Stage primaryStage) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("/sample/Login.fxml"));
       primaryStage.setTitle("Kron2");
       primaryStage.setScene(new Scene(root, 600, 350));
       primaryStage.show();
   }
   public static void main(String[] args){
       launch(args);
   }
}
