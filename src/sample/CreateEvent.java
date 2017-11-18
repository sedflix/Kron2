package sample;

import api.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.timetable.TimeTableController;
import sample.timetable.TimeTableGridPane;

import javafx.scene.text.Text;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class CreateEvent extends Application {
    private User user;
    private Parent root;
    private Event event;
    private boolean edit = false;

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
        primaryStage.setTitle("Kron2");

        Text text = (Text) root.lookup("#h1");

        Button button = (Button) root.lookup("#requestButton");

        if (edit) {
            text.setText("Edit Event");
            if (user.getDtype().equals("Student")) {
                button.setText("Edit Event/Book");
            } else {
                button.setText("Edit Event");
            }
        } else {
            text.setText("Create Event Request");
            if (user.getDtype().equals("Student")) {
                button.setText("Request Event/Book");
            } else {
                button.setText("Book Room");
            }
        }


        if (edit) {
            DatePicker datePicker = (DatePicker) root.lookup("#eventDatePicker");
            ChoiceBox<String> startBox = (ChoiceBox<String>) root.lookup("#eventStartTimePicker");
            ChoiceBox<String> endBox = (ChoiceBox<String>) root.lookup("#eventEndTimePicker");
            TextField eventName = (TextField) root.lookup("#eventNameField");
            TextField eventDescription = (TextField) root.lookup("#eventDescriptionField");
            TextField crowdSize = (TextField) root.lookup("#crowdSizeField");
            ComboBox<String> roomNumber = (ComboBox<String>) root.lookup("#roomNumberField");

            //TODO: string manipulation for data and time
            datePicker.setUserData(event.getDate());
            startBox.setValue(removeLastThree(event.getStartTime().toString()));
            endBox.setValue(removeLastThree(event.getEndTime().toString()));
            eventName.setText(event.getTagline());
            eventDescription.setText(event.getDescription());
            crowdSize.setText(event.getRoom().getCapacity() + "");
            roomNumber.setValue(event.getRoom().getRoomName());
        }

        button.setOnMouseClicked(event -> {

            DatePicker datePicker = (DatePicker) root.lookup("#eventDatePicker");
            ChoiceBox<String> startBox = (ChoiceBox<String>) root.lookup("#eventStartTimePicker");
            ChoiceBox<String> endBox = (ChoiceBox<String>) root.lookup("#eventEndTimePicker");
            TextField eventName = (TextField) root.lookup("#eventNameField");
            TextField eventDescription = (TextField) root.lookup("#eventDescriptionField");
            TextField crowdSize = (TextField) root.lookup("#crowdSizeField");
            ComboBox<String> roomNumber = (ComboBox<String>) root.lookup("#roomNumberField");

            LocalDate temp1 = datePicker.getValue();
            String startTime = startBox.getValue();
            String endTime = endBox.getValue();
            String name = eventName.getText();
            String description = eventDescription.getText();
            String size = crowdSize.getText();
            String room = roomNumber.getValue();


            if (temp1 != null && name != null && description != null && size != null && startTime != null && endTime != null && room != null) {
                int day = temp1.getDayOfMonth();
                int month = temp1.getMonthValue();
                int year = temp1.getYear();
                System.out.println(day+":"+month+":"+year);

                int tempIndex = startTime.indexOf(":");
                int startTimeHour = Integer.parseInt(startTime.substring(0, tempIndex));
                int startTimeMinute = Integer.parseInt(startTime.substring(tempIndex + 1));

                tempIndex = endTime.indexOf(":");
                int endTimeHour = Integer.parseInt(endTime.substring(0, tempIndex));
                int endTimeMinute = Integer.parseInt(endTime.substring(tempIndex + 1));

                Time starttime = new Time(startTimeHour, startTimeMinute, 00);
                Time endtime = new Time(endTimeHour, endTimeMinute, 00);
                Date date = new Date(year-1900, month, day);
                Room rooms = new Room(room);
                if (addEventNow(user, starttime, endtime, date, description, name, rooms)) {
                    button.setStyle("-fx-text-fill:  greenyellow");
                    primaryStage.close();
                } else {
                    button.setStyle("-fx-text-fill: red");
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

    public boolean addEventNow(User user, Time startTime, Time endTime, Date date, String description, String name, Room room) {
        this.user = user;
        if (user.getDtype().equals("Student")) {
            return ((Student) user).createEventRequest(name, room, description, startTime, endTime, date);
        } else if (user.getDtype().equals("Faculty")) {
            return ((Faculty) user).addEvent(name, room, description, startTime, endTime, date);
        } else if (user.getDtype().equals("Admin")) {
            return ((Admin) user).addEvent(name, room, description, startTime, endTime, date);
        }

        return false;
    }

    public boolean isEdit() {
        return edit;
    }
    public String removeLastThree(String toRemove){
        return toRemove.substring(0,toRemove.length()-3);
    }
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
