package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.custom.UserDAO;
import lk.ijse.orm.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        return true;

    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User ", User.class).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(userList);

    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        return true;

    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, entity);
        session.delete(user);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public User search(String name) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String getNextId() {
//        try {
//            Session session = FactoryConfiguration.getInstance().getSession();
//            Transaction transaction = session.beginTransaction();
//
//            Query<String> query = session.createQuery("SELECT MAX(userId) FROM User ", String.class);
//            String maxIdStr = query.uniqueResult();
//
//            transaction.commit();
//            session.close();
//
//            if (maxIdStr != null) {
//                int nextId = Integer.parseInt(maxIdStr) + 1;
//                return String.valueOf(nextId);
//            } else {
//                return "1";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "1";
//        }
        return null;
    }

}
