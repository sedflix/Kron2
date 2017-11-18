package sample.requests;

import api.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import org.hibernate.Session;

public class HandleRequestGridPane {

    User user;
    Admin admin;
    Faculty faculty;
    GridPane gridPanel;
    ListView<Event> listView = new ListView<>();
    ObservableList<Event> data; //= FXCollections.observableArrayList

    HandleRequestGridPane() {
        makeGrid();
    }

    public void makeGrid() {

        gridPanel = new GridPane();
        gridPanel.setHgap(0);
        gridPanel.setVgap(5);
        gridPanel.setGridLinesVisible(true);

        RowConstraints rc = new RowConstraints();
        //depends on number of requests
        rc.setPercentHeight(100);
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        ColumnConstraints cc = new ColumnConstraints();
        //depends on faculty of admin
        cc.setPercentWidth(100);
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        gridPanel.getColumnConstraints().add(cc);
        gridPanel.getRowConstraints().add(rc);


        Session session = MySession.getSession();
        admin = session.get(Admin.class, "ravi@iiitd.ac.in");

        data = FXCollections.observableArrayList(admin.getAllPendingRequests());
        listView.setItems(data);
        listView.setCellFactory(list -> new EventItem());


        gridPanel.add(listView, 0, 0);
    }


    public GridPane getGridPanel() {
        return gridPanel;
    }

    class EventItem extends ListCell<Event> {

        @Override
        public void updateItem(Event item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox hBox = new HBox();
                hBox.setSpacing(20);
//                hBox.setAlignment(Pos.CENTER);


                Label eventName = new Label(getEventString(item));
                eventName.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(eventName, new Insets(5, 50, 5, 10));

                HBox right = new HBox(20);


                Button approve = new Button("Approve");
                approve.setOnAction(actionEvent -> {
                    admin.approveEventRequest(item);
                });
                Button reject = new Button("Reject");
                reject.setOnAction(actionEvent -> {
                    admin.rejectEventRequest(item);
                });
                Button edit = new Button("Edit");


                right.setAlignment(Pos.CENTER_RIGHT);
                right.getChildren().addAll(approve, reject, edit);
                HBox.setHgrow(right, Priority.ALWAYS);
                HBox.setMargin(right, new Insets(5, 100, 5, 10));

//                if(user.getDtype().equals("Faculty")){
//
//                }
                hBox.getChildren().addAll(eventName);
                hBox.getChildren().addAll(right);
                setGraphic(hBox);

            }
        }

        public String getEventString(Event event) {
            String string = event.getTagline() +
                    "\tRoom: " + event.getRoom().getRoomName() +
                    "\tSeating Cap: " + event.getRoom().getCapacity() +
                    "\nDate: " + event.getDate() +
                    "\tStarting Time: " + event.getStartTime() +
                    "\tEnd Time: " + event.getEndTime() +
                    "\nRequested by: " + event.getCreators().getName();

            return string;

        }

    }
}
