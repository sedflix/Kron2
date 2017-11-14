package api;

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

    public List<Room> getAllRooms() {
        return new ArrayList<Room>();
    }

    public List<Room> getAvailableRooms(Date date) {
        return new ArrayList<Room>();
    }

    public List<Room> getAvailableRooms(Date date, Time startTime, Time endTime) {
        return new ArrayList<Room>();
    }

    public List<Room> getAvailableRoomOnwardsNow(){
        return new ArrayList<Room>();
    }

    public List<Room> getAvailableRoomOnwards(Date date, Time startTime){
        return new ArrayList<Room>();
    }
}
