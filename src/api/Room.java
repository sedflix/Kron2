package api;

import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Room {


    @Id
    @Column(nullable = false)
    private String roomName;

    @Column(nullable = true)
    private int capacity;


    public Room() {
    }

    public Room(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }


    public static List<Room> getAllRooms() {
        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Room");
        return (List<Room>) query.getResultList();
    }

    public Room(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @param date date
     * @return list of rooms that don't have any event on date
     */
    public static List<Room> getAvailableRoomsOn(Date date) {

        Session session = MySession.getSession();

        Calendar fromDate = Calendar.getInstance();
        fromDate.setTime(date);
        fromDate.set(Calendar.HOUR_OF_DAY, 0);
        fromDate.set(Calendar.MINUTE, 0);
        fromDate.set(Calendar.SECOND, 0);
        fromDate.set(Calendar.MILLISECOND, 0);

        Calendar toDate = Calendar.getInstance();
        toDate.setTime(date);
        toDate.set(Calendar.HOUR_OF_DAY, 23);
        toDate.set(Calendar.MINUTE, 59);
        toDate.set(Calendar.SECOND, 59);
        toDate.set(Calendar.MILLISECOND, 0);

        return getAvailableRoomsBetween(new Time(fromDate.getTimeInMillis()), new Time(toDate.getTimeInMillis()), new Date(fromDate.getTimeInMillis()));
    }

    public static List<Room> getAvailableRoomsBetween(Time startTime, Time endTime, Date date) {

        return Room.getAllRooms()
                .stream()
                .filter((o) ->
                        o.isFreeBetween(startTime, endTime, date))
                .collect(Collectors.toList());

    }

    public static List<Room> getAvailableRoomOnwardsNow() {
        return new ArrayList<Room>();
    }

    public static List<Room> getAvailableRoomOnwards(Date date, Time startTime) {
        return new ArrayList<Room>();
    }

    public static void main(String[] args) {
        Session session = MySession.getSession();
//        session.setCacheMode(CacheMode.GET);
//
//        List<Room> rooms = Room.getAllRooms();
//        rooms.forEach((o) -> {
//            System.out.println(o.getRoomName());
////            System.out.print("--");
////            System.out.println(o.isFreeBetween(session, Time.valueOf("01:00:00"), Time.valueOf("23:00:00"), Date.valueOf("2017-11-15")));
//
//        });
//
////        rooms = Room.getAvailableRoomsBetween(session, Time.valueOf("01:00:00"), Time.valueOf("23:00:00"), Date.valueOf("2017-11-15"));
////        rooms.forEach(o -> System.out.println(o.getRoomName()));
//        rooms = Room.getAvailableRoomsOn(Date.valueOf("2017-11-15"));
//        rooms.forEach(o -> System.out.println(o.getRoomName()));

        Time c = Time.valueOf("11:00:00");
        Time end = Time.valueOf("12:30:00");
        Date date = Date.valueOf("2018-01-01");
        System.out.println(c);
        System.out.println(end);
        System.out.println(date);
        boolean c21 = Room.isFreeBetween("C21", c, end, date);

        System.out.println(c21);
    }

    public static boolean isFreeBetween(String roomName, Time startTime, Time endTime, Date date) {
        Session session = MySession.getSession();
        Room room = session.get(Room.class, roomName.trim());
        return room.isFreeBetween(startTime, endTime, date);
    }

    public boolean isFreeBetween(Time startTime, Time endTime, Date date) {
        Session session = MySession.getSession();

        Query query = session.createQuery("select event from Event as event where event.room = :room " +
                "and( (event.startTime <= :startT and  event.endTime >= :endT)" +
                "or (event.startTime >= :startT and event.startTime <= :endT)" +
                "or (event.endTime >=:startT and event.endTime <= :endT) " +
                "or (event.startTime >= :startT and  event.endTime <= :endT))" +
                "and event.date = :dateT and event.isPending = false  " +
                "and event.isRejected = false  " +
                "and event.isCancelled = false ");

        query.setParameter("room", this);
        query.setParameter("startT", startTime);
        query.setParameter("endT", endTime);
        query.setParameter("dateT", date);

        if (query.getResultList().size() != 0) {

            return true;
        } else {
            query = session.createQuery("select event from CourseEvent as event where event.room = :room " +
                    "and (event.startTime >= :startT and event.endTime<= :endT) " +
                    "and event.dayOfWeek = :dayofweek");
            query.setParameter("room", this);
            query.setParameter("startT", startTime);
            query.setParameter("endT", endTime);
            query.setParameter("dayofweek", date.toLocalDate().getDayOfWeek());

            return query.getResultList().size() == 0;
        }

    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
