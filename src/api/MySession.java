package api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySession {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private MySession() {

    }

    public static Session getSession() {

        return ourSessionFactory.openSession();
    }


    public static boolean closeSessionFactory() {

        ourSessionFactory.close();
        return true;
    }
}
