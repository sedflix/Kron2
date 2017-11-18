package sample.requests;

import api.Admin;
import api.MySession;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class HandleRequestsController extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Requests");

        Session session = MySession.getSession();
//        Faculty admin = session.get(Faculty.class, "raj ayyar@iiitd.ac.in");
        Admin admin = session.get(Admin.class, "ravi@iiitd.ac.in");
        HandleRequestGridPane gridPane = new HandleRequestGridPane(admin, false, false, true);
        gridPane.update();
        Scene scene = new Scene(gridPane.getGridPanel(), 1200, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
