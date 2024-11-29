package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.custom.StudentProgramDetailsDAO;
import lk.ijse.orm.entity.StudentProgramDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentProgramDAOImpl implements StudentProgramDetailsDAO {
    private Session session;
    @Override
    public boolean save(StudentProgramDetails entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public ArrayList<StudentProgramDetails> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<StudentProgramDetails> studentProgramDetails = session.createQuery("FROM StudentProgramDetails ", StudentProgramDetails.class).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(studentProgramDetails);
    }

    @Override
    public boolean update(StudentProgramDetails entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public StudentProgramDetails search(String name) {
        Session session = null;
        Transaction transaction = null;
        StudentProgramDetails studentProgramDetails = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            String hql = "FROM StudentProgramDetails spd JOIN spd.student s WHERE s.name = :name";

            Query<StudentProgramDetails> query = session.createQuery(hql, StudentProgramDetails.class);
            query.setParameter("name", name);
            studentProgramDetails = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return studentProgramDetails;
    }




    @Override
    public boolean delete(int studentProgramId, Session session) {
         session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(studentProgramId);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<StudentProgramDetails> getStudentId(int i, Session session) {
        try {
            return session.createQuery("FROM StudentProgramDetails sp WHERE sp.student.st_id = :st_id", StudentProgramDetails.class)
                    .setParameter("st_id", i)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<StudentProgramDetails> searchh(String name) {
        Session session = null;
        Transaction transaction = null;
        List<StudentProgramDetails> studentProgramDetailsList = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            String hql = "FROM StudentProgramDetails spd JOIN FETCH spd.student s WHERE s.name = :name";

            Query<StudentProgramDetails> query = session.createQuery(hql, StudentProgramDetails.class);
            query.setParameter("name", name);
            studentProgramDetailsList = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return studentProgramDetailsList;
    }

}
