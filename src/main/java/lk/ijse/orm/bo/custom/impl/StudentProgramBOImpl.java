package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.StudentProgramBO;
import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dao.custom.StudentProgramDetailsDAO;
import lk.ijse.orm.dto.ProgramDTO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;
import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.StudentProgramDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentProgramBOImpl implements StudentProgramBO {

    StudentProgramDetailsDAO studentProgramDetailsDAO = (StudentProgramDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT_PROGRAM_DETAILS);


    @Override
    public ArrayList<StudentProgramDetailsDTO> getAllStudentPrograms() throws SQLException, ClassNotFoundException {
        List<StudentProgramDetailsDTO> allDetails= new ArrayList<>();
        List<StudentProgramDetails> all = studentProgramDetailsDAO.getAll();
        for (StudentProgramDetails sp : all) {
            allDetails.add(new StudentProgramDetailsDTO(sp.getStudent_program_id(),sp.getStudent(),sp.getProgram(),sp.getPayment(),sp.getRegistrationDate()));
        }
        return (ArrayList<StudentProgramDetailsDTO>) allDetails;
    }


}
