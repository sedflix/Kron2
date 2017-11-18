package sample.viewroom;

import api.Room;
import api.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.sql.Date;

public class ViewRoomController extends Application {
    private Parent root;
    private User user;
    @Override
    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/viewroom/fxml/ViewRoom.fxml"));
//        primaryStage.setTitle("Kron2");
//        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();

        Button button =(Button) root.lookup("#viewRoomButton");
        button.setOnMouseClicked(event -> {
            DatePicker datePicker = (DatePicker) root.lookup("#datePicker");
            ComboBox<String> startBox = (ComboBox<String>) root.lookup("#startTimePicker");
            ComboBox<String> endBox = (ComboBox<String>) root.lookup("#endTimePicker");
            ComboBox<String> roomBox = (ComboBox<String>) root.lookup("#roomPicker");

            LocalDate localDate = datePicker.getValue();
            String start = startBox.getValue();
            String end = endBox.getValue();
            String room = roomBox.getValue();

            if (localDate!=null && start!=null && end!=null && room!=null){
                int day = localDate.getDayOfMonth();
                int month = localDate.getMonthValue();
                int year = localDate.getYear();

                int tempIndex = start.indexOf(":");
                int startHour = Integer.parseInt(start.substring(0,tempIndex));
                int startMinute = Integer.parseInt(start.substring(tempIndex+1));

                tempIndex = end.indexOf(":");
                int endHour = Integer.parseInt(end.substring(0,tempIndex));
                int endMinute = Integer.parseInt(end.substring(tempIndex+1));

                Time startTime = new Time(startHour,startMinute,00);
                Time endTime = new Time(endHour,endMinute,00);

                Date date = new Date(year-1900,month,day);
//                System.out.println(date.getYear());

                if(Room.isFreeBetween(room,startTime,endTime,date)){
                    button.setStyle("-fx-text-fill: green");
                }
                else {
                    button.setStyle("-fx-text-fill: red");
                }

            }

        });


    }

    public Parent getRoot() {
        return root;
    }

    public static void main(String[] args){
        launch(args);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
