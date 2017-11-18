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

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CourseViewController extends Application {
    private User user;
    private Parent courseDescription;
    Course temp;
    private Button creditButton;
    private Button auditButton;

    public CourseViewController() throws IOException {

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * Couse Description
         */
        courseDescription = FXMLLoader.load(getClass().getResource("/sample/courseview/fxml/course_view.fxml"));
        creditButton = (Button) courseDescription.lookup("#creditButton");
        auditButton = (Button) courseDescription.lookup("#auditButton");
        ComboBox<String> comboBox = (ComboBox<String>) courseDescription.lookup("#search");

        comboBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                comboBox.getItems().clear();
                List<Course> listOfCourseWithKeyWords = Course.search(comboBox.getValue());
                listOfCourseWithKeyWords.forEach(course -> comboBox.getItems().add(course.getName()));
            }
        });

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
                    try {
                        setData(courseDescription, forInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
            return cell;
        });


        creditButton.setOnMouseClicked(event -> {
            try {
                creditControl();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        auditButton.setOnMouseClicked(event -> {
            try {
                auditControl();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        if (!user.getDtype().equals("Student")) {
            creditButton.setDisable(true);
            auditButton.setDisable(true);
        }

//        primaryStage.setScene(new Scene(courseDescription, 700, 900));
//        primaryStage.show();
    }

    public void setData(Parent courseDescription, Course forInfo) throws IOException {
        if (courseDescription == null) {
            courseDescription = FXMLLoader.load(getClass().getResource("/sample/courseview/fxml/course_view.fxml"));
            ;
        }

        if (creditButton == null) {
            creditButton = (Button) courseDescription.lookup("#creditButton");
            creditButton.setOnMouseClicked(event -> {
                try {
                    creditControl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        if (auditButton == null) {
            auditButton = (Button) courseDescription.lookup("#auditButton");
            auditButton.setOnMouseClicked(event -> {
                try {
                    auditControl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("in set data" + forInfo.getCourseCode());
        String facultyString = "Faculty: ";
        Set<Faculty> temp = forInfo.getFaculties();
        Hibernate.initialize(temp);
        this.temp = forInfo;
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

        Text credits = new Text("Credits: " + Integer.toString(forInfo.getCredits()));
        credits.setId("credits");
        TextFlow creditsTF = (TextFlow) courseDescription.lookup("#credits");
        creditsTF.getChildren().clear();
        creditsTF.getChildren().add(credits);

        TextArea coTA = (TextArea) courseDescription.lookup("#co");
        coTA.clear();
        coTA.setText(processedString(forInfo.getPostConditions()));

        Student student = (Student) user;
        if (student.hasRegisteredForCourse(forInfo)) {
            creditButton.setText("- Credit");
            creditButton.setStyle("-fx-text-fill: red;");
        } else {
            creditButton.setText("+ Credit");
            creditButton.setStyle("-fx-text-fill: green;");
        }

        if (student.hasAuditedCourse(forInfo)) {
            auditButton.setText("- Audit");
            auditButton.setStyle("-fx-text-fill: red;");
        } else {
            auditButton.setText("+ Audit");
            auditButton.setStyle("-fx-text-fill: green;");
        }

    }

    public Parent getCourseDescription() {
        return courseDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void creditControl() throws IOException {
        switch (user.getDtype()) {
            case "Student":
                Student student = ((Student) user);
                if (student.hasRegisteredForCourse(temp)) {
                    student.deleteRegisteredCourse(temp);
                } else {
                    student.insertRegisteredCourse(temp);
                }
                break;
            case "Faculty":
                break;
            case "Admin":
                break;
        }
        setData(courseDescription, temp);
    }

    public void auditControl() throws IOException {
        switch (user.getDtype()) {
            case "Student":
                Student student = ((Student) user);
                if (student.hasAuditedCourse(temp)) {
                    student.deleteAuditedCourse(temp);
                } else {
                    student.insertAuditedCourse(temp);
                }
                break;
            case "Faculty":
                break;
            case "Admin":
                break;
        }
        setData(courseDescription, temp);
    }

    public Course getTemp() {
        return temp;
    }

    public void setTemp(Course course) {
        temp = course;
    }

    public String processedString(String conditions) {
        int index = conditions.indexOf(":");
        String toReturn = "";
        while (index > 0) {
            toReturn += "--";
            toReturn += conditions.substring(0, index).trim();
            toReturn += "\n";
            conditions = conditions.substring(index + 1);
            index = conditions.indexOf(":");
        }
        return toReturn;
    }
}
