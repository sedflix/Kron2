package api;

import org.hibernate.Session;

public class UserAuthentication {

    //null means failed
    public static User login(String email, String password) {

        User user = User.getUserByEmail(email);
        if (!user.getPassword().equals(password)) {
            return null;
        }
        Session session = MySession.getSession();
        switch (user.getDtype()) {
            case "Admin":
                user = session.get(Admin.class, email);
                break;
            case "Faculty":
                user = session.get(Faculty.class, email);
                break;
            case "Student":
                user = session.get(Student.class, email);
                break;
            default:
                user = null;
        }
        return user;
    }


    public static boolean isEmailVaild(String email) {

        if (User.getUserByEmail(email) == null) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordVaild(String email, String password) {
        if (User.getUserByEmail(email).getPassword().equals(password)) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        UserAuthentication userAuthentication = new UserAuthentication();
        Student user = (Student) userAuthentication.login("siddharth16268@iiitd.ac.in", "[{Sid@123}]");
        System.out.println(user.getName());
    }
}
