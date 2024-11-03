package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.custom.ProgramDAO;
import lk.ijse.orm.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    private Session session;
    @Override
    public boolean save(Program entity) throws SQLException, ClassNotFoundException{
         session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return false;

    }

    @Override
    public ArrayList<Program> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Perform HQL query to retrieve all customers
        List<Program> programList = session.createQuery("FROM Program ", Program.class).list();

        // Commit the transaction and close the session
        transaction.commit();
        session.close();

        // Convert List to ArrayList
        return new ArrayList<>(programList);
    }
    @Override
    public boolean update(Program entity) throws SQLException, ClassNotFoundException {
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
    public Program getById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(String programId, String programName, String duration, double fee) {
        return false;
    }

    @Override
    public boolean update(String programId, String programName, String duration, double fee) {
        return false;
    }
}
