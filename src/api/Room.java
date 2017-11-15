package api;

import org.hibernate.CacheMode;
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


    public static List<Room> getAllRooms(Session session) {
        Query query = session.createQuery("FROM Room");
        return (List<Room>) query.getResultList();
    }

    public Room(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @param date    date
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

        Session session = MySession.getSession();
        return Room.getAllRooms(session)
                .stream()
                .filter((o) ->
                        o.isFreeBetween(session, startTime, endTime, date))
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
        session.setCacheMode(CacheMode.GET);

        List<Room> rooms = Room.getAllRooms(session);
        rooms.forEach((o) -> {
            System.out.println(o.getRoomName());
//            System.out.print("--");
//            System.out.println(o.isFreeBetween(session, Time.valueOf("01:00:00"), Time.valueOf("23:00:00"), Date.valueOf("2017-11-15")));

        });

//        rooms = Room.getAvailableRoomsBetween(session, Time.valueOf("01:00:00"), Time.valueOf("23:00:00"), Date.valueOf("2017-11-15"));
//        rooms.forEach(o -> System.out.println(o.getRoomName()));
        rooms = Room.getAvailableRoomsOn(Date.valueOf("2017-11-15"));
        rooms.forEach(o -> System.out.println(o.getRoomName()));

    }

    public boolean isFreeBetween(Session session, Time startTime, Time endTime, Date date) {

        Query query = session.createQuery("select event from Event as event where event.room = :room and (event.startTime >= :start and event.endTime <= :endT) and event.date = :dateE");
        query.setParameter("room", this);
        query.setParameter("start", startTime);
        query.setParameter("endT", endTime);
        query.setParameter("dateE", date);
        return query.getResultList().size() == 0;

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
