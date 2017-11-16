package api;


import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Column
    private String name;

    @Column(unique = true, nullable = true)
    private String rollNumber;

    @Id
    @Column(unique = true)
    private String email;


    @Column
    private String password;


    @OneToMany(mappedBy = "creators")
    private Set<Event> eventCreated = new HashSet<>();


    public User() {
    }

    public boolean deleteEventRequest(Session session, Event event) {
        try {

            if (event.getCreators().getEmail().equals(this.getEmail())) {
                session.beginTransaction();
                session.delete(event);
                session.getTransaction().commit();
            } else {
                throw new Exception("The event was not created by you");
            }
        } catch (Exception e) {
            System.out.println("failed to delete " + e.getMessage());
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

