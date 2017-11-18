package api;


import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Column(insertable = false, updatable = false)
    private String dtype;

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

    public static User getUserByEmail(String email) {
        return MySession.getSession().get(User.class, email.trim());
    }

    public List<Event> getMyRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Event event where event.creators = :user");
        query.setParameter("user", this);
        return (List<Event>) query.getResultList();
    }

    public List<Event> getMyPendingRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Event event where event.creators = :user and event.isPending = true");
        query.setParameter("user", this);
        return (List<Event>) query.getResultList();
    }

    public List<Event> getMyRejectedRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Event event where event.creators = :user and event.isRejected = true");
        query.setParameter("user", this);
        return (List<Event>) query.getResultList();
    }

    public List<Event> getMyCancelledRequests() {
        Session session = MySession.getSession();
        Query query = session.createQuery("FROM Event event where event.creators = :user and event.isCancelled = true");
        query.setParameter("user", this);
        return (List<Event>) query.getResultList();
    }


    public boolean deleteEventRequest(Event event) {

        Session session = MySession.getSession();
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

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public Set<Event> getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(Set<Event> eventCreated) {
        this.eventCreated = eventCreated;
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
        this.rollNumber = rollNumber.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

