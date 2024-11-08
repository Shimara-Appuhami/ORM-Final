package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.StudentBO;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(dto.getSt_id(),dto.getName(),dto.getAddress(),dto.getDob(),dto.getContact(),dto.getEmail(),dto.getGender(),dto.getRegistrationDate(),dto.getAdvance()));
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = (ArrayList<Student>) studentDAO.getAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student dto : students) {
            StudentDTO studentDTO = new StudentDTO(dto.getSt_id(),dto.getName(),dto.getAddress(),dto.getDob(),dto.getContact(),dto.getEmail(),dto.getGender(),dto.getRegistrationDate(),dto.getAdvance());

            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
        }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getSt_id(),dto.getName(),dto.getAddress(),dto.getDob(),dto.getContact(),dto.getEmail(),dto.getGender(),dto.getRegistrationDate(),dto.getAdvance()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }


}
