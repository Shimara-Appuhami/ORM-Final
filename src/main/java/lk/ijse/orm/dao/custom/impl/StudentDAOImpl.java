package lk.ijse.orm.dao.custom.impl;

import org.hibernate.Session;
import lk.ijse.orm.config.FactoryConfiguration;
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

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        return true;


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
