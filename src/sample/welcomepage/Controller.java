package sample.welcomepage;

import api.Event;
import api.MySession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.login.LoginController;
import sample.signup.SignupController;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class Controller extends Application {
    public static void main(String[] args) {
        System.out.println("---\n");
        System.out.println("Starting to create to database");
        System.out.println("---\n");
        Session session = MySession.getSession();
        Query query = session.createQuery("select event from Event as event where event.isPending = true");
        List<Event> eventList = query.getResultList();
        eventList.forEach(event -> {
            if (Math.abs(event.getCreationTime().getDate() - new Date().getDate()) > 5) {
                session.beginTransaction();
                session.delete(event);
                session.getTransaction().commit();
                session.evict(event);
            }
        });
        System.out.println("---\n");
        System.out.println("End connecting to database");
        System.out.println("---\n");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("IIITD ROOM MANAGEMENT");
        Parent welcomeWindow = FXMLLoader.load(getClass().getResource("/sample/welcomepage/fxml/welcome_page.fxml"));
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
