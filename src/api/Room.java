package api;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {

    private int roomID;
    private String roomName;
    private int capacity;

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
