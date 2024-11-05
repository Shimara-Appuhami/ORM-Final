package lk.ijse.orm.dao.custom.impl;

import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.custom.ProgramDAO;
import lk.ijse.orm.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    private Session session;

    @Override
    public boolean save(Program entity) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true; // Return true to indicate successful save
    }

    @Override
    public ArrayList<Program> getAll() throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Program> programList = session.createQuery("FROM Program", Program.class).list();
        transaction.commit();
        session.close();
        return new ArrayList<>(programList);
    }

    @Override
    public boolean update(Program entity) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true; // Return true to indicate successful update
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program program = session.get(Program.class, id);
        if (program != null) {
            session.delete(program);
            transaction.commit();
            session.close();
            return true; // Return true to indicate successful deletion
        }
        session.close();
        return false; // Return false if no program found with given id
    }

    @Override
    public Program search(String name) {
        // You can implement this method if needed
        return null;
    }

    @Override
    public Program findByName(String programName) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String hql = "FROM Program p WHERE p.program_name = :programName";
            Query<Program> query = session.createQuery(hql, Program.class);
            query.setParameter("programName", programName);
            return query.uniqueResult(); // Returns null if no results are found
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving program by name: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    @Override
    public Program findById(String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.get(Program.class, programId); // Assuming programId is the primary key
    }


}
