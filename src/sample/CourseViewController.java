package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CourseViewController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/course_view.fxml"));

        Text courseHeading = new Text("Course Name");
        courseHeading.setId("course_heading");
        TextFlow txetflow = (TextFlow) root.lookup("#course_heading");
        txetflow.getChildren().add(courseHeading);


        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
