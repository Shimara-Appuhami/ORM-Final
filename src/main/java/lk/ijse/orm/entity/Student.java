package lk.ijse.orm.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student") // Specify the table name
public class Student implements Serializable {

    @Id
    private String st_id;
    private String name;
    private String address;
    private String dob;
    private String contact;
    private String email;
    private String gender;
    private String registrationDate;
    private String advance;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<StudentProgramDetails> studentProgramDetails = new ArrayList<>();

    public Student() {}

    public Student(String st_id, String name, String address, String dob, String contact, String email, String gender, String registrationDate, String advance, List<StudentProgramDetails> studentProgramDetails) {
        this.st_id = st_id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.registrationDate = registrationDate;
        this.advance = advance;
        this.studentProgramDetails = studentProgramDetails;
    }

    public Student(String stId, String name, String address, String dob, String contact, String email, String gender, String registrationDate, String advance) {
        this.st_id = stId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.registrationDate = registrationDate;
        this.advance = advance;
    }

    public Student(String stId, String name, String address, String dob, String contact, String email, String gender, String string, double advance) {
        this.st_id = stId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.registrationDate = string;
        this.advance = String.valueOf(advance);
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public List<StudentProgramDetails> getStudentProgramDetails() {
        return studentProgramDetails;
    }

    public void setStudentProgramDetails(List<StudentProgramDetails> studentProgramDetails) {
        this.studentProgramDetails = studentProgramDetails;
    }

    @Override
    public String toString() {
        return "Student{" +
                "st_id='" + st_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", advance='" + advance + '\'' +
                ", studentProgramDetails=" + studentProgramDetails +
                '}';
    }
}
