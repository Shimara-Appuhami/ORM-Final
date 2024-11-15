package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.StudentBO;
import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.ProgramDAO;
import lk.ijse.orm.dao.custom.StudentDAO;
import lk.ijse.orm.dao.custom.StudentProgramDetailsDAO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;
import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.entity.StudentProgramDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    StudentProgramDetailsDAO studentProgramDetailsDAO = (StudentProgramDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT_PROGRAM_DETAILS);

    @Override
    public boolean addStudent(StudentDTO dto, List<StudentProgramDetailsDTO> programDetails) throws SQLException, ClassNotFoundException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Student student = new Student(dto.getSt_id(), dto.getName(), dto.getAddress(), dto.getDob(),
                    dto.getContact(), dto.getEmail(), dto.getGender(),
                    dto.getRegistrationDate(), dto.getAdvance());

            studentDAO.save(student);

            for (StudentProgramDetailsDTO details : programDetails) {
                StudentProgramDetails studentProgramDetails = new StudentProgramDetails();
                studentProgramDetails.setStudent(student); // Set the saved student
                studentProgramDetails.setPayment(Double.parseDouble(details.getPayment())); // Convert and set payment
                studentProgramDetails.setRegistrationDate(details.getRegistrationDate());

                Program program = programDAO.findById(details.getProgram_id());
                if (program != null) {
                    studentProgramDetails.setProgram(program); // Set the program entity
                } else {
                    throw new Exception("Program not found with ID: " + details.getProgram_id());
                }

                studentProgramDetailsDAO.save(studentProgramDetails);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }


    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = (ArrayList<Student>) studentDAO.getAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student dto : students) {
            StudentDTO studentDTO = new StudentDTO(dto.getSt_id(), dto.getName(), dto.getAddress(), dto.getDob(), dto.getContact(), dto.getEmail(), dto.getGender(), dto.getRegistrationDate(), dto.getAdvance());

            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getSt_id(), dto.getName(), dto.getAddress(), dto.getDob(), dto.getContact(), dto.getEmail(), dto.getGender(), dto.getRegistrationDate(), dto.getAdvance()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        Transaction transaction = null;
        boolean isDeleted = false;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Begin transaction
            transaction = session.beginTransaction();

            // Fetch associated StudentProgramDetails for the given student ID
            List<StudentProgramDetails> details = studentProgramDetailsDAO.getStudentId(Integer.parseInt(id), session);

            // Delete each associated StudentProgramDetails record
            for (StudentProgramDetails detail : details) {
                if (!studentProgramDetailsDAO.delete(detail.getStudent_program_id(), session)) {
                    // If deleting a student program detail fails, rollback and return false
                    transaction.rollback();
                    return false;
                }
            }

            // Now delete the student record
            isDeleted = studentDAO.delete(id, session);
            if (isDeleted) {
                // Commit transaction if student and associated details are successfully deleted
                transaction.commit();
            } else {
                // Rollback transaction if student deletion fails
                transaction.rollback();
            }
        } catch (Exception e) {
            // In case of an exception, rollback the transaction to maintain data integrity
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return isDeleted;
    }

    public StudentDTO findByName(String name) {
        Student student = studentDAO.search(name);
        if (student != null) {
            return new StudentDTO(
                    student.getSt_id(),
                    student.getName(),
                    student.getAddress(),
                    student.getDob(),
                    student.getContact(),
                    student.getEmail(),
                    student.getGender(),
                    student.getRegistrationDate(),
                    student.getAdvance()
            );
        }
        return null;
    }

    @Override
    public Student findById(String id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getNextId() {
        return studentDAO.getNextId();
    }


}
