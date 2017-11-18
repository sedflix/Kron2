package sample.courseview;

import api.Course;
import api.Faculty;
import api.Student;
import api.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

public class CourseViewController extends Application{
    private User user;
    private Parent courseDescription;
    Course temp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {

        /**
         * Couse Description
         */
        courseDescription = FXMLLoader.load(getClass().getResource("/sample/courseview/fxml/course_view.fxml"));

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

            cell.setOnMousePressed(event -> {
                if (!cell.isEmpty()) {
                    Course forInfo = Course.getCourseByName(cell.getItem());
                    setTemp(forInfo);
                    setData(courseDescription, forInfo);
                }

            });
            return cell ;
        });

        Button creditButton = (Button) courseDescription.lookup("#creditButton");
        creditButton.setOnMouseClicked(event -> {
            creditControl();
        });

        Button auditButton = (Button) courseDescription.lookup("#auditButton");
        auditButton.setOnMouseClicked(event -> {
            auditControl();
        });


//        primaryStage.setScene(new Scene(courseDescription, 700, 900));
//        primaryStage.show();
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
        coTA.setText(processedString(forInfo.getPostConditions()));
    }
    public Parent getCourseDescription(){
        return courseDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTemp(Course course){
        temp=course;
    }
    public void creditControl(){
        if (user.getDtype().equals("Student")) {
            ((Student) user).insertRegisteredCourse(temp);
        } else if (user.getDtype().equals("Faculty")) {

        } else if (user.getDtype().equals("Admin")) {

        }
    }


    public void auditControl(){
        if (user.getDtype().equals("Student")) {
            ((Student) user).insertAuditedCourse(temp);
        } else if (user.getDtype().equals("Faculty")) {

        } else if (user.getDtype().equals("Admin")) {

        }
    }

    public String processedString(String conditions){
        int index = conditions.indexOf(":");
        String toReturn = "";
        while(index>0){
            toReturn+="--";
            toReturn+=conditions.substring(0,index).trim();
            toReturn+="\n";
            conditions = conditions.substring(index+1);
            index = conditions.indexOf(":");
        }
        return toReturn;
    }
}
