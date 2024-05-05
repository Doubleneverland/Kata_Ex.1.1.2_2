package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    Util factory = new Util();



    @Override
    public void createUsersTable() {
        org.hibernate.Transaction transaction = null;
        try (Session session = factory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            String sqlCreateUT = "CREATE TABLE IF NOT EXISTS users2 (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20)," +
                    " lastName VARCHAR(20), age TINYINT)";
            Query query = session.createSQLQuery(sqlCreateUT);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        org.hibernate.Transaction transaction = null;
        try (Session session = factory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            String sqlDropUT = "DROP TABLE IF EXISTS users2";
            Query query = session.createSQLQuery(sqlDropUT);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override

    public void saveUser(String name, String lastName, byte age) {

        org.hibernate.Transaction transaction = null;
        try (Session session = factory.getFactory().openSession()) {
            transaction = (org.hibernate.Transaction) session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем- " + name + " добавлен в базу данных.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override

    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = factory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        org.hibernate.Transaction transaction = null;
        try (Session session = factory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            transaction.commit();
            for (User user : users) {
                System.out.println(user);
            }
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        org.hibernate.Transaction transaction = null;
        try (Session session = factory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE users2").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
