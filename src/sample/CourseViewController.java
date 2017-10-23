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


        Text faculty = new Text("Faculty: Vivek Gupta, Anubha");
        faculty.setId("faculty");
        TextFlow facultyTF = (TextFlow) root.lookup("#faculty");
        facultyTF.getChildren().add(faculty);

        Text pre = new Text("Preqs: CS201, CS301");
        pre.setId("prereq");
        TextFlow preTF = (TextFlow) root.lookup("#prereq");
        preTF.getChildren().add(pre);

        Text credits = new Text("Credits: 4");
        credits.setId("credits");
        TextFlow creditsTF = (TextFlow) root.lookup("#credits");
        creditsTF.getChildren().add(credits);


        TextFlow coTF = (TextFlow) root.lookup("#co");


        Text co = new Text("Course Objective \n");
        co.setId("co_h2");

        Text co2 = new Text("CO 1: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. " +
                "Gibberish, alternatively jibberish, jibber-jabber, or gobbledygook, is language that is (or appears to be) nonsense. It may include speech sounds that are not actual words,[1] or language games and specialized jargon that seems nonsensical to outsiders.[2] Gibberish should not be confused with literary nonsense such as that used in the poem \"Jabberwocky\" by Lewis Carroll.[citation needed]\n" +
                "The word gibberish is more commonly applied to informal speech, while gobbledygook (sometimes gobbledegook, gobbledigook or gobbledegoo) is more often applied to writing or language that is meaningless or is made unintelligible by excessive use of abstruse technical terms.[citation needed] \"Officialese\", \"legalese\", or \"bureaucratese\" are forms of gobbledygook. The related word jibber-jabber refers to rapid talk that is difficult to understand.[3]\n" +
                "\n" +
                "Etymology[edit]\n" +
                "The term gibberish was first seen in English in the early 16th century.[4] Its etymology is not certain, but it is generally thought to be an onomatopoeia imitative of speech, similar to the words jabber (wkhgphilivjnbjkgb." +
                "fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb.\n" +
                "CO 3: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. \n" +
                "CO 4: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. \n" +
                "CO 5: fhsdkcakgweb kwhjgvjbbjkslnvljfdvrvh kgmwilbejvnflkhb wkgljbkjflnvlbkh frwkhgphilivjnbjkgb. \n ");
        co2.setId("c0_main");
        coTF.getChildren().addAll(co, co2);

//        co.setId("co");



        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }
}
