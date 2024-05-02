package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    Util factory = new Util();



    @Override
    public void createUsersTable() {
        Session session =null;
        try {
            session = factory.getFactory().openSession();
            session.beginTransaction();
            String sqlCreateUT = "CREATE TABLE IF NOT EXISTS users2 (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20)," +
                    " lastName VARCHAR(20), age TINYINT)";
            Query query = session.createSQLQuery(sqlCreateUT);
            query.executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            String sqlDropUT = "DROP TABLE IF EXISTS users2";
            Query query = session.createSQLQuery(sqlDropUT);
            query.executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override

    public void saveUser(String name, String lastName, byte age) {

        Session session = null;
        try {
            session = factory.getFactory().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);

            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем- " + name + " добавлен в базу данных.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override

    public void removeUserById(long id) {
        Session session = null;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            for (User u : users) {
                System.out.println(u);
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        Session session = null;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE users2").executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
}
