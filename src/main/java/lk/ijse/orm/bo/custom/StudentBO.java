package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBo {

    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;
    StudentDTO findByName(String name) throws SQLException, ClassNotFoundException;


}
