package sample.timetable;

import api.Course;
import api.CourseEvent;
import api.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.CourseViewController;

import java.util.ArrayList;

public class TimeTableGridPane {

    private int startTime;
    private int endTime;
    private String dayName[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private ListView<CourseEvent>[][] calendar;
    private ObservableList<CourseEvent>[][] calenderList;
    private int[][] clashingEvents;
    private StackPane[] times;
    private StackPane[] days;
    private GridPane gridPane;

    public TimeTableGridPane() {
        this.startTime = 8;
        this.endTime = 17;
        makeGridPane();
    }

    public TimeTableGridPane(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        makeGridPane();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public GridPane makeGridPane() {

        gridPane = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(5);
        gridPane.setGridLinesVisible(false);

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100 / 6);
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100 / (endTime - startTime + 1) * 2);
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);


        days = new StackPane[6];
        for (int i = 0; i < 6; i++) {
            if (i != 0) {
                days[i] = new StackPane();
                days[i].getChildren().add(new Label(dayName[i - 1]));
                gridPane.add(days[i], 0, i);
            }
            gridPane.getRowConstraints().add(rc);
        }


        times = new StackPane[2 * (endTime - startTime + 1)];
        gridPane.getColumnConstraints().add(cc);
        for (int i = 1; i < times.length; i++) {
            times[i] = new StackPane();
            times[i].getChildren().addAll(new Label(startTime + (int) (i / 2) + ""));
            gridPane.add(times[i], i, 0);
            gridPane.getColumnConstraints().add(cc);
        }


        calendar = new ListView[7][2 * (endTime - startTime + 1)];
        clashingEvents = new int[calendar.length][calendar[0].length];
        calenderList = new ObservableList[calendar.length][calendar[0].length];


        for (int i = 1; i < calendar.length; i++) {
            for (int j = 1; j < calendar[0].length; j++) {

                calendar[i][j] = new ListView<CourseEvent>();
                calenderList[i][j] = FXCollections.observableList(new ArrayList<>());
                calendar[i][j].setItems(calenderList[i][j]);


                calendar[i][j].setCellFactory(list -> new CourseItem());


                //EventListener this is
                calendar[i][j].getSelectionModel().selectedItemProperty().addListener(
                        (ov, old_val, new_val) -> {
                            CourseViewController courseViewController = new CourseViewController();
                            Stage tempStage = new Stage();
                            try {
                                Parent parent = FXMLLoader.load(getClass().getResource("/sample/course_view.fxml"));
                                courseViewController.setData(parent, ov.getValue().getCourse());
                                tempStage.setScene(new Scene(parent));
                                tempStage.show();
                            } catch (Exception e) {

                            }
//                            System.out.println(ov.getValue().getCourse().getName());
                        });
                gridPane.add(calendar[i][j], j, i);
            }
        }


        return gridPane;
    }


    public int getX(CourseEvent course) {
        return course.getDayOfWeek().getValue();
    }

    public int[] getY(CourseEvent courseEvent) {
        String startTime = courseEvent.getStartTime().toString();
        String endTime = courseEvent.getEndTime().toString();

        int startHour = Integer.parseInt(startTime.substring(0, startTime.indexOf(":")));
        int endHour = Integer.parseInt(endTime.substring(0, endTime.indexOf(":")));
        int startMinutes = Integer.parseInt(startTime.substring(startTime.indexOf(":") + 1, startTime.indexOf(":", startTime.indexOf(":") + 1)));
        int endMinutes = Integer.parseInt(endTime.substring(endTime.indexOf(":") + 1, endTime.indexOf(":", endTime.indexOf(":") + 1)));

        int startIndex = 2 * (startHour - this.startTime) + (int) (startMinutes / 30);
        int endIndex = 2 * (endHour - this.startTime) + (int) (endMinutes / 30);

        return new int[]{startIndex, endIndex - 1};
    }

    public void addAllCoureseOfStudent(Student student) {
        student.getAllCourses().forEach(this::addCourse);
    }

    public void addAllCourses() {
        Course.getAllCourses().forEach(this::addCourse);
    }

    public void addCourse(Course course) {
        course.getCourseEvents().forEach(this::addCourseEvent);
    }

    public void addCourseEvent(CourseEvent courseEvent) {
        int x = getX(courseEvent);
        int y[] = getY(courseEvent);

        for (int i = y[0]; i <= y[1]; i++) {
            clashingEvents[x][i]++;
            calenderList[x][i].add(courseEvent);
            calendar[x][i].setItems(calenderList[x][i]);

        }
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String[] getDayName() {
        return dayName;
    }

    public void setDayName(String[] dayName) {
        this.dayName = dayName;
    }

    public ListView<CourseEvent>[][] getCalendar() {
        return calendar;
    }

    public void setCalendar(ListView<CourseEvent>[][] calendar) {
        this.calendar = calendar;
    }

    public ObservableList<CourseEvent>[][] getCalenderList() {
        return calenderList;
    }

    public void setCalenderList(ObservableList<CourseEvent>[][] calenderList) {
        this.calenderList = calenderList;
    }

    public int[][] getClashingEvents() {
        return clashingEvents;
    }

    public void setClashingEvents(int[][] clashingEvents) {
        this.clashingEvents = clashingEvents;
    }

    public StackPane[] getTimes() {
        return times;
    }

    public void setTimes(StackPane[] times) {
        this.times = times;
    }

    public StackPane[] getDays() {
        return days;
    }

    public void setDays(StackPane[] days) {
        this.days = days;
    }

    static class CourseItem extends ListCell<CourseEvent> {
        @Override
        public void updateItem(CourseEvent item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                Label x = new Label(item.getCourse().getCourseCode() + "-" + item.getRoom().getRoomName());
                x.setStyle("-fx-background-color: " + calculateColorBase(item.getCourse().getName()));
                setGraphic(x);
            }
        }

        public String calculateColorBase(String name) {
            String opacity = "#99"; //opacity between 00-ff
            String hexColor = String.format(
                    opacity + "%06X", (0xeeeeee & name.hashCode()));

            return hexColor;
        }
    }
}
