package lk.ijse.orm.dto;


import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.Student;

public class StudentProgramDetailsDTO{
    private String student_program_id;
    private String st_id;
    private String program_id;
    private String payment;
    private String registration_date;

    public StudentProgramDetailsDTO() {
    }

    public StudentProgramDetailsDTO(String student_program_id,String st_id, String program_id, String payment, String registration_date) {
        this.student_program_id = student_program_id;
        this.st_id = st_id;
        this.program_id = program_id;
        this.payment = payment;
        this.registration_date = registration_date;
    }

    public StudentProgramDetailsDTO(int studentProgramId, Student student, Program program, double payment, String registrationDate) {
        this.student_program_id = String.valueOf(studentProgramId);
        this.st_id = student.getSt_id();
        this.program_id = program.getProgram_id();
        this.payment = String.valueOf(payment);
        this.registration_date = registrationDate;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getStudent_program_id() {
        return student_program_id;
    }

    public void setStudent_program_id(String student_program_id) {
        this.student_program_id = student_program_id;
    }

    @Override
    public String toString() {
        return "StudentProgramDetailsDTO{" +
                "student_program_id='" + student_program_id + '\'' +
                ", st_id='" + st_id + '\'' +
                ", program_id='" + program_id + '\'' +
                ", payment='" + payment + '\'' +
                ", registration_date='" + registration_date + '\'' +
                '}';
    }
}
