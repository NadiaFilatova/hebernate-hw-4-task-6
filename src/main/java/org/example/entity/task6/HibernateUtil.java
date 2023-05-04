package org.example.entity.task6;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory factory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry registry =  new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            factory = configuration.buildSessionFactory(registry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
