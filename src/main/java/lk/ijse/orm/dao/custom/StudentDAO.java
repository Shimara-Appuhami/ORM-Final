package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Student;
import org.hibernate.Session;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student> {

    boolean save(Student entity);
    Student generateNextId(String id);

    String getNextId();

    boolean delete(String id, Session session);

    int getStudentCount();
}

