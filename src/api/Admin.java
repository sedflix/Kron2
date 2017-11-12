package api;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Admin extends User {

    public Admin(String name, String rollNumber, String email, String password, Group groupType) {
        super(name, rollNumber, email, password, groupType);
    }

    public Admin() {
    }

    public  List<Event> getAllRequests() {
        return new ArrayList<Event>();
    }

    public List<Event> getAllPendingRequests(){
        return new ArrayList<Event>();
    }

    public boolean approveEventRequest(Event event){
        return true;
    }

    public boolean rejectEventRequest(Event event) {
        return true;
    }

    public boolean addEvent(Event event) {
        return true;
    }

    public boolean cancelEvent(Event event){
        return true;
    }

}
