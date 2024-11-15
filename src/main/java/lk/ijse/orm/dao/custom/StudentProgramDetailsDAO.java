package lk.ijse.orm.dao.custom;

import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.StudentProgramDetails;
import org.hibernate.Session;

import java.util.List;


public interface StudentProgramDetailsDAO extends CrudDAO<StudentProgramDetails> {

    boolean delete(int studentProgramId, Session session);

    List<StudentProgramDetails> getStudentId(int i, Session session);
}
