package sample;

import api.Course;
import api.Faculty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import sample.timetable.TimeTableController;
import sample.timetable.TimeTableGridPane;

import java.util.List;
import java.util.Set;

public class CourseViewController extends Application{
    private Parent courseDescription;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {

        /**
         * Couse Description
         */
        courseDescription = FXMLLoader.load(getClass().getResource("/sample/course_view.fxml"));

        ComboBox<String> comboBox = (ComboBox<String>) courseDescription.lookup("#search");

        comboBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                comboBox.getItems().clear();
                List<Course> listOfCourseWithKeyWords = Course.search(comboBox.getValue());
                listOfCourseWithKeyWords.forEach(course -> comboBox.getItems().add(course.getName()));
            }});

        comboBox.setCellFactory(list -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };

            cell.setOnMousePressed(e -> {
                if (! cell.isEmpty()) {
                    Course forInfo = Course.getCourseByName(cell.getItem());

                    setData(courseDescription, forInfo);
                }
            });
            return cell ;
        });



//        primaryStage.setScene(new Scene(courseDescription, 700, 900));
        primaryStage.show();
    }

    public void setData(Parent courseDescription, Course forInfo) {
        String facultyString = "Faculty: ";
        Set<Faculty> temp = forInfo.getFaculties();
        Hibernate.initialize(temp);

        if (!temp.isEmpty()) {
            for (Faculty fac : temp) {
                Hibernate.initialize(fac);
                facultyString += fac.getName();
            }
        }
        Text faculty = new Text(facultyString);
        faculty.setId("faculty");
        TextFlow facultyTF = (TextFlow) courseDescription.lookup("#faculty");
        facultyTF.getChildren().clear();
        facultyTF.getChildren().add(faculty);

        String preReq = forInfo.getPreConditions();
        Text pre = new Text(preReq);
        pre.setId("prereq");
        TextFlow preTF = (TextFlow) courseDescription.lookup("#prereq");
        preTF.getChildren().clear();
        preTF.getChildren().add(pre);

        Text credits = new Text("Credits: "+Integer.toString(forInfo.getCredits()));
        credits.setId("credits");
        TextFlow creditsTF = (TextFlow) courseDescription.lookup("#credits");
        creditsTF.getChildren().clear();
        creditsTF.getChildren().add(credits);

        TextArea coTA = (TextArea) courseDescription.lookup("#co");
        coTA.clear();
        coTA.setText(forInfo.getPostConditions());
    }
    public Parent getCourseDescription(){
        return courseDescription;
    }
}
