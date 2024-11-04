package lk.ijse.orm.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "program") // Specify the table name
public class Program implements Serializable {

    @Id
    private String program_id;
    private String program_name;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<StudentProgramDetails> studentDetails = new ArrayList<>();

    public Program() {}

    public Program(String program_id, String program_name, String duration, double fee, List<StudentProgramDetails> studentDetails) {
        this.program_id = program_id;
        this.program_name = program_name;
        this.duration = duration;
        this.fee = fee;
        this.studentDetails = studentDetails;
    }

    public Program(String programId, String programName, String duration, double fee) {
        this.program_id = programId;
        this.program_name = programName;
        this.duration = duration;
        this.fee = fee;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<StudentProgramDetails> getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(List<StudentProgramDetails> studentDetails) {
        this.studentDetails = studentDetails;
    }

    @Override
    public String toString() {
        return "Program{" +
                "program_id='" + program_id + '\'' +
                ", program_name='" + program_name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", studentDetails=" + studentDetails +
                '}';
    }
}
