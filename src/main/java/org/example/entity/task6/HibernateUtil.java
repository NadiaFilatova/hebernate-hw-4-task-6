package org.example.entity.task6;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public final class HibernateUtil {
    private static SessionFactory factory;

    private HibernateUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    static {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            factory = configuration.buildSessionFactory(registry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static <T> T doInTransaction(Function<Session, T> action) {
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            return action.apply(session);
        } catch (Exception e) {
            log.error("Failed to execute action in transaction", e);
            throw new RuntimeException(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public static void doInTransaction(Consumer<Session> action) {
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            action.accept(session);
        } catch (Exception e) {
            log.error("Failed to execute action in transaction", e);
            throw new RuntimeException(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
