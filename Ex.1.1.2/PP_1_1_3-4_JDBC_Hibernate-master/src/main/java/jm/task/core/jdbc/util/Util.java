package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Util() {

    }
    private final String URL ="jdbc:mysql://localhost:3306/pp_ex.1.1.2";
    private final String USERNAME = "root";
    private final String PASSWORD = "double";
    private Connection connection;
    {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.deregisterDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    // реализуйте настройку соеденения с БД
}
