package api;

public class Faculty extends User {
    public Faculty(String emailId) {
        super(emailId);
    }

    public boolean addEvent(Event event) {
        return true;
    }


}
