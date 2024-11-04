package lk.ijse.orm.dao.custom;

import com.mysql.cj.Session;
import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Student;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student> {

    boolean save(Student entity);

}

