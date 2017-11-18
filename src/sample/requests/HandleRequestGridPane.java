package sample.requests;

import api.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.CreateEvent;

public class HandleRequestGridPane {

    private User user;
    private Admin admin;
    private Faculty faculty;
    private Student student;
    private GridPane gridPanel;
    private ListView<Event> listView = new ListView<>();
    private ObservableList<Event> data; //= FXCollections.observableArrayList
    private boolean pendinng = false;
    private boolean rejected = false;
    private boolean accepted = false;
    private boolean all = true;

    HandleRequestGridPane(User user, boolean pending, boolean rejected, boolean all) {
        setUser(user);
        makeGrid();
    }

    public void makeGrid() {

        gridPanel = new GridPane();


        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(10);
        rc.setFillHeight(false);
        rc.setVgrow(Priority.NEVER);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100);
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        gridPanel.getColumnConstraints().add(cc);
        gridPanel.getRowConstraints().add(rc);

        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);


        Button myRequests = new Button("My Events");
        myRequests.setOnAction(actionEvent -> {
            this.data = FXCollections.observableArrayList(this.user.getMyRequests());
            update();
        });
        hbox.getChildren().add(myRequests);

        /*
            Buttons for Admin
         */
        if (admin != null) {

            Button getAllEvents = new Button("All Events");
            getAllEvents.setOnAction(actionEvent -> {
                this.data = FXCollections.observableArrayList(this.admin.getAllRequests());
                update();
            });
            hbox.getChildren().add(getAllEvents);

            Button seeAllPending = new Button("All Pending Requests");
            seeAllPending.setOnAction(actionEvent -> {
                this.data = FXCollections.observableArrayList(this.admin.getAllPendingRequests());
                update();
            });
            hbox.getChildren().add(seeAllPending);

            Button getAllRejected = new Button("All Rejected Requests");
            getAllRejected.setOnAction(actionEvent -> {
                this.data = FXCollections.observableArrayList(this.admin.getAllRejectdRequests());
                update();
            });
            hbox.getChildren().add(getAllRejected);


        }

        /*
            Buttons for Students
         */
        if (student != null) {
            Button myPendingRequests = new Button("My Pending Requests");
            myPendingRequests.setOnAction(actionEvent -> {
                this.data = FXCollections.observableArrayList(this.user.getMyPendingRequests());
                update();
            });
            hbox.getChildren().add(myPendingRequests);


            Button myRejectedRequests = new Button("My Rejected Requests");
            myRejectedRequests.setOnAction(actionEvent -> {
                this.data = FXCollections.observableArrayList(this.user.getMyRejectedRequests());
                update();
            });
            hbox.getChildren().add(myRejectedRequests);
        }


        gridPanel.add(hbox, 0, 0);


        rc = new RowConstraints();
        rc.setPercentHeight(90);
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        gridPanel.getRowConstraints().add(rc);

        GridPane.setVgrow(listView, Priority.ALWAYS);
        GridPane.setFillHeight(listView, true);
        GridPane.setValignment(listView, VPos.TOP);
        gridPanel.add(listView, 0, 1);
    }

    public void setUser(User user) {
        this.user = user;
        if (user.getDtype().equals("Student")) {
            this.student = (Student) user;
        } else if (user.getDtype().equals("Faculty")) {
            this.faculty = (Faculty) user;
        } else if (user.getDtype().equals("Admin")) {
            this.admin = (Admin) user;
        }
    }

    public void update() {
        listView.setItems(this.data);
        listView.setCellFactory(list -> new EventItem());
        listView.autosize();
    }


    public void setData() {
        if (pendinng) {
            if (student != null) {
                this.data = FXCollections.observableArrayList(this.student.getPendingEventRequests());
            } else if (admin != null) {
                this.data = FXCollections.observableArrayList(this.admin.getAllPendingRequests());
            }
        } else if (all) {
            if (student != null) {
                this.data = FXCollections.observableArrayList(this.student.getMyRequests());
            } else if (faculty != null) {
                this.data = FXCollections.observableArrayList(this.faculty.getMyRequests());
            } else if (admin != null) {
                this.data = FXCollections.observableArrayList(this.admin.getAllRequests());
            }
        }
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


                if (admin != null) {
                    if (item.isPending() || item.isRejected()) {
                        Button approve = new Button("Approve");
                        approve.setOnAction(actionEvent -> {
                            admin.approveEventRequest(item);
                            update();
                        });
                        right.getChildren().add(approve);
                    }
                    if (!item.isRejected()) {
                        Button reject = new Button("Reject");
                        reject.setOnAction(actionEvent -> {
                            admin.rejectEventRequest(item);
                            update();
                        });
                        right.getChildren().add(reject);
                    }

                }

                /*
                    EDIT

                    Add listener for edit
                */
                Button edit = new Button("Edit");
                //TODO: Add listner for edit
                edit.setOnAction(actionEvent -> {
                    CreateEvent createEventController = new CreateEvent();
                    createEventController.setEdit(true);
                    createEventController.setUser(user);
                    createEventController.setEvent(item);
                    Stage tempStage = new Stage();
                    try {
                        createEventController.start(tempStage);
                        Scene scene = new Scene(createEventController.getRoot(), 1360, 768);
                        tempStage.setScene(scene);
                        tempStage.show();

                        /**
                         * bad code
                         */
                        Session session = MySession.getSession();
                        session.beginTransaction();
                        session.delete(item);
                        session.getTransaction().commit();
                        /**
                         * Bad code
                         */


                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    update();
                });


                if ((!item.isPending() && student != null) || (faculty != null) || (admin != null)) {
                    right.getChildren().add(edit);
                }

                /*
                delete
                 */
                Button delete = new Button("Delete");
                delete.setOnAction(actionEvent -> {
                    user.deleteEventRequest(item);
                    data.removeAll(item);
                    update();
                });
                right.getChildren().add(delete);


                right.setAlignment(Pos.CENTER_RIGHT);
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
