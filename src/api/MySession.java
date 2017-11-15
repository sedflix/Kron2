package api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySession {

    private static SessionFactory ourSessionFactory;

    private MySession() {

    }

    public static Session getSession() {

        if (ourSessionFactory == null) {
            try {
                ourSessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        return ourSessionFactory.getCurrentSession();
    }

    public static boolean closeSession() {
        if (ourSessionFactory == null) {
            return false;
        }

        if (ourSessionFactory.isOpen()) {
            ourSessionFactory.getCurrentSession().close();
            return true;
        } else {
            return true;
        }
    }

    public static boolean closeSessionFactory() {
        if (ourSessionFactory == null) {
            return false;
        }

        ourSessionFactory.close();
        return true;
    }
}
