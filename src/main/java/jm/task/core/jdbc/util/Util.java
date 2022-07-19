package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {
    private static final String DB_USER_NAME = "Konovalov";
    private static final String DB_PASSWORD = "Konovalov";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, DB_CONNECTION_URL);
                settings.put(Environment.USER, DB_USER_NAME);
                settings.put(Environment.PASS, DB_PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return sessionFactory;
    }

    public static void closeSessionFactory() {
        if(sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }

    }
}
