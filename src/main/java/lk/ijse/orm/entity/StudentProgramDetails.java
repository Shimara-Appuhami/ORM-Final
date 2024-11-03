package lk.ijse.orm.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "StudentProgramDetails")
public class StudentProgramDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_program_id;

    @ManyToOne
    @JoinColumn(name = "st_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private double payment;
    private String registrationDate;

    public StudentProgramDetails() {}

    public StudentProgramDetails(int student_program_id, Student student, Program program, double payment, String registrationDate) {
        this.student_program_id = student_program_id;
        this.student = student;
        this.program = program;
        this.payment = payment;
        this.registrationDate = registrationDate;
    }

    public int getStudent_program_id() {
        return student_program_id;
    }

    public void setStudent_program_id(int student_program_id) {
        this.student_program_id = student_program_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "StudentProgramDetails{" +
                "student_program_id=" + student_program_id +
                ", student=" + student +
                ", program=" + program +
                ", payment=" + payment +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
}
