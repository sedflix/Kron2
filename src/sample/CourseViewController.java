package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CourseViewController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        /**
         * Couse Description
         */
        Parent courseDescription = FXMLLoader.load(getClass().getResource("/sample/course_view.fxml"));

        Text courseHeading = new Text("Course Name");
        courseHeading.setId("course_heading");
        TextFlow txetflow = (TextFlow) courseDescription.lookup("#course_heading");
        txetflow.getChildren().add(courseHeading);


        Text faculty = new Text("Faculty: Vivek Gupta, Anubha");
        faculty.setId("faculty");
        TextFlow facultyTF = (TextFlow) courseDescription.lookup("#faculty");
        facultyTF.getChildren().add(faculty);

        Text pre = new Text("Preqs: CS201, CS301");
        pre.setId("prereq");
        TextFlow preTF = (TextFlow) courseDescription.lookup("#prereq");
        preTF.getChildren().add(pre);

        Text credits = new Text("Credits: 4");
        credits.setId("credits");
        TextFlow creditsTF = (TextFlow) courseDescription.lookup("#credits");
        creditsTF.getChildren().add(credits);


        TextArea coTA = (TextArea) courseDescription.lookup("#co");


        coTA.setText("CO 1: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. " +
                "Gibberish, alternatively jibberish, jibber-jabber, or gobbledygook, is language that is (or appears to be) nonsense. It may include speech sounds that are not actual words,[1] or language games and specialized jargon that seems nonsensical to outsiders.[2] Gibberish should not be confused with literary nonsense such as that used in the poem \"Jabberwocky\" by Lewis Carroll.[citation needed]\n" +
                "The word gibberish is more commonly applied to informal speech, while gobbledygook (sometimes gobbledegook, gobbledigook or gobbledegoo) is more often applied to writing or language that is meaningless or is made unintelligible by excessive use of abstruse technical terms.[citation needed] \"Officialese\", \"legalese\", or \"bureaucratese\" are forms of gobbledygook. The related word jibber-jabber refers to rapid talk that is difficult to understand.[3]\n" +
                "\n" +
                "Etymology[edit]\n" +
                "The term gibberish was first seen in English in the early 16th century.[4] Its etymology is not certain, but it is generally thought to be an onomatopoeia imitative of speech, similar to the words jabber (wkhgphilivjnbjkgb." +
                "fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb.\n" +
                "CO 3: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. \n" +
                "CO 4: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. \n" +
                "CO 5: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. \n ");

//        co.setId("co");


        ScrollPane scrollPane = (ScrollPane) courseDescription.lookup("#scroll_time_table");
        Node timeTable = FXMLLoader.load(getClass().getResource("/sample/TimeTable.fxml"));

        scrollPane.setContent(timeTable);

        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(courseDescription, 700, 900));
        primaryStage.show();
    }
}
