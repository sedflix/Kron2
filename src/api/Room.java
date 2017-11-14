package api;

import org.hibernate.Session;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomID;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = true)
    private int capacity;


    public  Room() {
    }

    public Room(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }


    public static List<Room> getAllRooms(Session session) {
        Query query = session.createQuery("FROM Room");
        return (List<Room>) query.getResultList();
    }

    /**
     * @param session Hibernate session
     * @param date    date
     * @return list of rooms that don't have any event on date
     */
    public static List<Room> getAvailableRoomsOn(Session session, Date date) {
        Query query = session.createQuery("select Room from Room, Event inner join Event.room where not (Event.date = :dateE)");
        query.setParameter("dateE", date);
        return (List<Room>) query.getResultList();
    }

    public static List<Room> getAvailableRoomsBetween(Session session, Date startTime, Date endTime, Date date) {
        Query query = session.createQuery("select Room from Room, Event inner join Event.room where not ( Event.startTime >= :start and Event.endTime <= :endT and Event.date = :dateE )");
        query.setParameter("start", startTime);
        query.setParameter("endT", endTime);
        query.setParameter("dateE", date);
        return (List<Room>) query.getResultList();
    }

    public static List<Room> getAvailableRoomOnwardsNow() {
        return new ArrayList<Room>();
    }

    public static List<Room> getAvailableRoomOnwards(Date date, Time startTime) {
        return new ArrayList<Room>();
    }

    public static void main(String[] args) {

    }

    public boolean isFreeBetween(Session session, Date startTime, Date endTime, Date date) {
        Query query = session.createQuery("from Event where Event.room = :room and (Event.startTime >= :start and Event.endTime <= :endT) and Event.date = :dateE");
        query.setParameter("room", this);
        query.setParameter("start", startTime);
        query.setParameter("endT", endTime);
        query.setParameter("dateE", date);
        return query.getResultList().size() == 0;
    }


    public Room(String roomName){
        this.roomName = roomName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
