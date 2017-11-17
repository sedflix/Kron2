package sample;


import api.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import org.hibernate.Session;

import java.io.IOException;

public class SignupController extends Application{
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Signup.fxml"));
        primaryStage.setTitle("Kron2");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();

        Button button = (Button) root.lookup("#registerButton");

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextField nameField = (TextField) root.lookup("#nameField");
                TextField emailField = (TextField) root.lookup("#emailField");
                TextField typeField = (TextField) root.lookup("#typeField");
                PasswordField passwordField = (PasswordField) root.lookup("#passwordField");

                String name = nameField.getText();
                String email = emailField.getText();
                String type = typeField.getText();
                String password = passwordField.getText();

                if (name!=null && email!=null && type!=null && password!=null){
                    Session session = MySession.getSession();
                    session.beginTransaction();
                    if (type.toLowerCase().equals("student")){
                        Student student = new Student();
                        student.setName(name);
                        student.setEmail(email);
                        student.setPassword(password);
                        session.saveOrUpdate(student);
                    }
                    else if (type.toLowerCase().equals("admin")){
                        Admin admin = new Admin();
                        admin.setName(name);
                        admin.setEmail(email);
                        admin.setPassword(password);
                        session.saveOrUpdate(admin);
                    }
                    else if (type.toLowerCase().equals("faculty")){
                        Faculty faculty = new Faculty();
                        faculty.setName(name);
                        faculty.setEmail(email);
                        faculty.setPassword(password);
                        session.saveOrUpdate(faculty);
                    }
                    session.getTransaction().commit();
                    session.close();
                }

            }
        });
    }
    public static void main(String[] args){
        launch(args);
    }
}
