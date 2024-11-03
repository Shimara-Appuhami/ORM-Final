package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.StudentProgramBO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;

import java.util.ArrayList;

public class StudentProgramBOImpl implements StudentProgramBO {


    @Override
    public boolean addStudentProgram(StudentProgramDetailsDTO dto) {
        return false;
    }

    @Override
    public ArrayList<StudentProgramDetailsDTO> getAllStudentPrograms() {
        return null;
    }

    @Override
    public boolean updateStudentProgram(StudentProgramDetailsDTO dto) {
        return false;
    }

    @Override
    public boolean deleteStudentProgram(StudentProgramDetailsDTO dto) {
        return false;
    }
}
