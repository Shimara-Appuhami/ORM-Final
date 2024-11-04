package lk.ijse.orm.dao;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{

    public boolean save(T entity)throws SQLException,ClassNotFoundException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String entity) throws SQLException, ClassNotFoundException;
    T search(String name);
}
