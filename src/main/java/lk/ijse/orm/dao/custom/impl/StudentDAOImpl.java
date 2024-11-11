package lk.ijse.orm.dao.custom.impl;

import org.hibernate.Session;
import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.entity.Student;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;
    @Override
    public boolean save(Student entity) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        return true;


    }

    @Override
    public Student generateNextId(String id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use an HQL query to find the next student where ID is greater than the given ID
            String hql = "FROM Student s WHERE s.st_id > :id ORDER BY s.st_id ASC";
            return session.createQuery(hql, Student.class)
                    .setParameter("id", id)
                    .setMaxResults(1)  // Limit to one result to get only the next student
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();

            // Query to get the maximum numeric ID from the table
            Query<String> query = session.createQuery("SELECT MAX(st_id) FROM Student", String.class);
            String maxIdStr = query.uniqueResult();

            transaction.commit();
            session.close();

            if (maxIdStr != null) {
                // Parse maxIdStr to integer and increment it
                int nextId = Integer.parseInt(maxIdStr) + 1;
                return String.valueOf(nextId); // Convert back to String
            } else {
                // If no records exist, start with "1"
                return "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "1"; // Default to "1" in case of an error
        }
    }




//    @Override
//    public Student findByName(String name) throws SQLException, ClassNotFoundException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Student student = session.get(Student.class, name);
//        transaction.commit();
//        session.close();
//        return student;
//
//    }


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
        Student student = session.get(Student.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String name) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "FROM Student WHERE name = :name";
            return session.createQuery(hql, Student.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
