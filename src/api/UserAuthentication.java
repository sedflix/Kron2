package api;

import static api.Group.ADMIN;
import static api.Group.FACULTY;
import static api.Group.STUDENT;

public class UserAuthentication {

    //null means failed
    public User login(String email, String password) {

        User user;
        Group group = null;
        switch (group) {
            case ADMIN:
                user = new Admin(email);
                break;
            case FACULTY:
                user = new Faculty(email);
                break;
            case STUDENT:
                user = new Student(email);
                break;
            default:
                user = null;
        }

        return user;
    }

    public boolean isEmailVaild(String email) {
        return true;
    }

}
