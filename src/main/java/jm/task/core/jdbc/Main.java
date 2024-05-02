package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Tantyana", "Fedorova", (byte) 25);
        userDao.saveUser("Tantyana", "Fedorova", (byte) 25);
        userDao.saveUser("Tantyana", "Fedorova", (byte) 25);
        userDao.saveUser("Olga", "Petrova",(byte) 34);
        userDao.saveUser("Igor", "Serov", (byte) 26);
        userDao.saveUser("Nikolay", "Egorov", (byte) 23);
        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();


        // реализуйте алгоритм здесь
    }
}
