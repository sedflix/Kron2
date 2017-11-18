package sample;

import api.MySession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller extends Application {
    public static void main(String[] args) {
        System.out.println("---\n");
        System.out.println("Starting to create to database");
        System.out.println("---\n");
        MySession.getSession();
        System.out.println("---\n");
        System.out.println("End connecting to database");
        System.out.println("---\n");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("IIITD ROOM MANAGEMENT");
        Parent welcomeWindow = FXMLLoader.load(getClass().getResource("/sample/welcome_page.fxml"));
        primaryStage.setScene(new Scene(welcomeWindow));

        Button loginButton = (Button) welcomeWindow.lookup("#loginRedirect");
        loginButton.setOnMouseClicked(event -> {
            LoginController login = new LoginController();
            try {
                login.start(primaryStage);
            }
            catch (Exception e){

            }
        });

        Button signUpButton = (Button) welcomeWindow.lookup("#signUpRedirect");
        signUpButton.setOnMouseClicked(event -> {
            SignupController signupController = new SignupController();
            try {
                signupController.start(primaryStage);
            }
            catch (Exception e){

            }
        });

        primaryStage.show();
    }
}
