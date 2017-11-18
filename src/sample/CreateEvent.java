package sample;

import api.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.timetable.TimeTableController;
import sample.timetable.TimeTableGridPane;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class CreateEvent extends Application {
    private User user;
    private Parent root;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/sample/create_event.fxml"));

        ScrollPane scrollPane = (ScrollPane) root.lookup("#scroll_time_table");
//        Node timeTable = FXMLLoader.load(getClass().getResource("/sample/TimeTable.fxml"));
        TimeTableController temp = new TimeTableController();
        temp.start(primaryStage);
        TimeTableGridPane timeTable = temp.getGridPane();
        scrollPane.setContent(timeTable.getGridPane());
//        primaryStage.setTitle("Kron2");


        Button button = (Button) root.lookup("#requestButton");

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                DatePicker datePicker = (DatePicker) root.lookup("#eventDatePicker");
                ChoiceBox<String> startBox  =   (ChoiceBox<String>) root.lookup("#eventStartTimePicker");
                ChoiceBox<String> endBox    =   (ChoiceBox<String>) root.lookup("#eventEndTimePicker");
                TextField eventName         =   (TextField) root.lookup("#eventNameField");
                TextField eventDescription  =   (TextField) root.lookup("#eventDescriptionField") ;
                TextField crowdSize         =   (TextField) root.lookup("#crowdSizeField");
                ComboBox<String> roomNumber =   (ComboBox<String>) root.lookup("#roomNumberField");

                LocalDate temp = datePicker.getValue();
                String startTime = startBox.getValue();
                String endTime = endBox.getValue();
                String name = eventName.getText();
                String description = eventDescription.getText();
                String size = crowdSize.getText();
                String room = roomNumber.getValue();


                if (temp!=null && name!=null && description!=null && size!=null && startTime!=null && endTime!=null && room!=null) {
                    int day = temp.getDayOfMonth();
                    int month = temp.getMonthValue();
                    int year = temp.getYear();

                    int tempIndex = startTime.indexOf(":");
                    int startTimeHour = Integer.parseInt(startTime.substring(0,tempIndex));
                    int startTimeMinute = Integer.parseInt(startTime.substring(tempIndex+1));

                    tempIndex = endTime.indexOf(":");
                    int endTimeHour = Integer.parseInt(endTime.substring(0,tempIndex));
                    int endTimeMinute = Integer.parseInt(endTime.substring(tempIndex+1));

                    Time starttime = new Time(startTimeHour,startTimeMinute,00);
                    Time endtime = new Time(endTimeHour,endTimeMinute,00);
                    Date date = new Date(year,month,day);
                    Room rooms = new Room(room);
                    setUser(user,starttime,endtime,date,description,name,rooms);
                }
            }
        });


//        primaryStage.setScene(new Scene(root, 900, 900));
        primaryStage.show();
    }

    public Parent getRoot() {
        return root;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user,Time startTime, Time endTime, Date date, String description,String name,Room room) {
        this.user = user;
        if (user.getDtype().equals("Student")) {
            ((Student) user).createEventRequest(name,room,description,startTime,endTime,date);
        } else if (user.getDtype().equals("Faculty")) {
            ((Faculty) user).addEvent(name,room,description,startTime,endTime,date);
        } else if (user.getDtype().equals("Admin")) {
            ((Admin) user).addEvent(name,room,description,startTime,endTime,date);
        }
    }
}
