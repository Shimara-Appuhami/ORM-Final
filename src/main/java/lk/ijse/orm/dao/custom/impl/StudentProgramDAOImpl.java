package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.custom.StudentProgramDetailsDAO;
import lk.ijse.orm.entity.StudentProgramDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentProgramDAOImpl implements StudentProgramDetailsDAO {
    private Session session;
    @Override
    public boolean save(StudentProgramDetails entity) {

        return false;
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
        return null;
    }


}