package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Util() {
    }
//    private final String URL ="jdbc:mysql://localhost:3306/pp_ex.1.1.2";
//    private final String USERNAME = "root";
//    private final String PASSWORD = "double";
//    private Connection connection;
//    {
//        try {
//            Driver driver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.deregisterDriver(driver);
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            if (!connection.isClosed()) {
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }


    private final SessionFactory factory = new Configuration()
            .configure()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();


    public SessionFactory getFactory() {
        return factory;
    }

    // реализуйте настройку соеденения с БД
}
