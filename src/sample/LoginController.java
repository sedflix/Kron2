package sample;

import api.Student;
import api.User;
import api.UserAuthentication;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginController extends Application{
   @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("/sample/Login.fxml"));
       primaryStage.setScene(new Scene(root, 600, 350));
       primaryStage.show();

       Button button = (Button) root.lookup("#loginButton");
       button.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               TextField textField = (TextField) root.lookup("#email");
               PasswordField passwordField = (PasswordField) root.lookup("#password");

               String mail = textField.getText();
               String pass = passwordField.getText();
               if (mail!=null && pass!=null) {
                   User user = UserAuthentication.login(mail, pass);
                   if (user == null){
                       System.out.println("Invalid Email or Password");
                   }
                   else {
                       if (user.getDtype().equals("Student")){
                           StudentPage studentPage = new StudentPage();
                           try{
                               studentPage.start(primaryStage);
                           }
                           catch (Exception e){

                           }
                       }
                       else if (user.getDtype().equals("Faculty")){
                           FacultyPage facultyPage = new FacultyPage();
                           try {
                               facultyPage.start(primaryStage);
                           }
                           catch (Exception e){

                           }
                       }
                       else if (user.getDtype().equals("Admin")){
                           AdminPage adminPage = new AdminPage();
                           try {
                               adminPage.start(primaryStage);
                           }
                           catch (Exception e){

                           }
                       }
                   }
               }
           }
       });
   }
   public static void main(String[] args){
       launch(args);
   }
}
