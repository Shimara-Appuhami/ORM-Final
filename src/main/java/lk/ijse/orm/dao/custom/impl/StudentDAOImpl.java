package lk.ijse.orm.dao.custom.impl;

import org.hibernate.Session;
import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.entity.Student;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;
    @Override
    public boolean save(Student entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
            return true; // Return true only if save and commit are successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back on exception
            }
            e.printStackTrace(); // Log the exception (consider using a logger in production)
            return false; // Indicate failure
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }
    }


    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Student> studentList = session.createQuery("FROM Student", Student.class).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(studentList);
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String id)  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Student getById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
