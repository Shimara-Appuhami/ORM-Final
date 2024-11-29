package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentProgramBO extends SuperBo {
    //    public boolean addStudentProgram(StudentProgramDetailsDTO dto);
    public ArrayList<StudentProgramDetailsDTO>getAllStudentPrograms() throws SQLException, ClassNotFoundException;

    StudentProgramDetailsDTO search(String studentName);
    public List<StudentProgramDetailsDTO> searchh(String name) ;
//    public boolean updateStudentProgram(StudentProgramDetailsDTO dto);
//    public boolean deleteStudentProgram(StudentProgramDetailsDTO dto);

}
