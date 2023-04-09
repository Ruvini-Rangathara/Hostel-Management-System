package com.d24.hms.util;


import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import com.d24.hms.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.Reader;
import java.util.Properties;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;


 private FactoryConfiguration() {
     StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.loadProperties("hibernate.properties");
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistryBuilder.build());
        metadataSources.
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Room.class).
                addAnnotatedClass(Reservation.class).
                addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null ? factoryConfiguration=new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
