package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util connect = new Util();
    Connection connection = connect.getConnection();

    public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sqlCreateUT = "CREATE TABLE IF NOT EXISTS users2 (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20)," +
                    " lastName VARCHAR(20), age TINYINT)";
            statement.executeUpdate(sqlCreateUT);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }

        }
    }

    public void dropUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sqlDropUT = "DROP TABLE IF EXISTS users2";
            statement.executeUpdate(sqlDropUT);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sqlSaveU = "insert into users2 (name, lastName, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(sqlSaveU)) {
            connect.getConnection().setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connect.getConnection().commit();
            System.out.println("User с именем- " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connect.getConnection() != null) {
                connect.getConnection().rollback();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sqlRemoveU = "DELETE FROM users2 WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveU)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> list = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users2";

        try (Statement statement = connect.getConnection().createStatement()) {
            connect.getConnection().setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
            connect.getConnection().commit();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (connect.getConnection() != null) {
                connect.getConnection().rollback();
            }
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sqlCleanUT = "TRUNCATE TABLE users2";
            statement.executeUpdate(sqlCleanUT);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }

    }
}
