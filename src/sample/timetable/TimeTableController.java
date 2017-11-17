package sample.timetable;

import api.Course;
import api.CourseEvent;
import api.MySession;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.ArrayList;


// 8
// 19

public class TimeTableController extends Application {


    private static int startTime = 8;
    private static int endTime = 18;
    private ListView<CourseEvent>[][] calendar;
    private ObservableList<CourseEvent>[][] calenderList;
    private int[][] clashingEvents;
    private StackPane[] times;
    private StackPane[] days;

    public static int getX(CourseEvent course) {
        return course.getDayOfWeek().getValue() + 1;
    }

    public static int[] getY(CourseEvent courseEvent) {
        String startTime = courseEvent.getStartTime().toString();
        String endTime = courseEvent.getEndTime().toString();

        int startHour = Integer.parseInt(startTime.substring(0, startTime.indexOf(":")));
        int endHour = Integer.parseInt(endTime.substring(0, endTime.indexOf(":")));
        int startMinutes = Integer.parseInt(startTime.substring(startTime.indexOf(":") + 1, startTime.indexOf(":", startTime.indexOf(":") + 1)));
        int endMinutes = Integer.parseInt(endTime.substring(endTime.indexOf(":") + 1, endTime.indexOf(":", endTime.indexOf(":") + 1)));

        int startIndex = 2 * (startHour - TimeTableController.startTime) + (int) (startMinutes / 30);
        int endIndex = 2 * (endHour - TimeTableController.startTime) + (int) (endMinutes / 30);

        return new int[]{startIndex, endIndex - 1};

    }

    public static int getStartTime() {
        return startTime;
    }

    public static void setStartTime(int startTime) {
        TimeTableController.startTime = startTime;
    }

    public static int getEndTime() {
        return endTime;
    }

    public static void setEndTime(int endTime) {
        TimeTableController.endTime = endTime;
    }

    public static void main(String[] args) {
//        Time x = new Time(new Date().getTime());
//        Session session = MySession.getSession();
        //       CourseEvent courseEvent = session.get(CourseEvent.class, 5);
//        System.out.println(courseEvent.getStartTime());
//        System.out.println(courseEvent.getEndTime());
//        int y [] = getY(courseEvent);
//        System.out.println(y[0] + "-" + y[1]);
//        System.out.println(getX(courseEvent));
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Calendar");


        GridPane gridPane = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(5);
//        gridPane.setGridLinesVisible(true);

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100 / 7);
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100 / (endTime - startTime + 1) * 2);
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);


        days = new StackPane[7];
        for (int i = 0; i < 7; i++) {
            if (i != 0) {
                days[i] = new StackPane();
                days[i].getChildren().add(new Label(i + " . "));
                gridPane.add(days[i], 0, i);
            }
            gridPane.getRowConstraints().add(rc);
        }


        times = new StackPane[2 * (endTime - startTime + 1)];
        for (int i = 0; i < times.length; i++) {
            if (i != 0) {
                times[i] = new StackPane();
                times[i].getChildren().addAll(new Label(startTime + (int) (i / 2) + " - " + i));
                gridPane.add(times[i], i, 0);
            }
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
                calendar[i][j].setCellFactory(list -> new ColorRectCell());
                gridPane.add(calendar[i][j], j, i);
            }
        }

        Session session = MySession.getSession();
        addCourse(session.get(Course.class, "NT"));
        addCourse(session.get(Course.class, "AP"));
        addCourse(session.get(Course.class, "CO"));

        Scene scene = new Scene(gridPane, 480, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addCourse(Course course) {
        course.getCourseEvents().parallelStream().forEach(this::addCourseEvent);
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

    public ListView[][] getCalendar() {
        return calendar;
    }

    public void setCalendar(ListView[][] calendar) {
        this.calendar = calendar;
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

    static class ColorRectCell extends ListCell<CourseEvent> {
        @Override
        public void updateItem(CourseEvent item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                Label x = new Label(item.getCourse().getCourseCode());
                setGraphic(x);
            }
        }
    }
}
