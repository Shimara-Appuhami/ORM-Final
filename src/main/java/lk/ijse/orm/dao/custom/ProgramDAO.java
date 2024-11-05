package lk.ijse.orm.dao.custom;

import com.mysql.cj.Session;
import lk.ijse.orm.dao.CrudDAO;
import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.Student;

public interface ProgramDAO extends CrudDAO<Program> {
    Program findByName(String programName)throws Exception;
    public Program findById(String programId);

}
