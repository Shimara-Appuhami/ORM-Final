package lk.ijse.orm.dto;




public class StudentProgramDetailsDTO{
    private String st_id;
    private String course_id;
    private String payment;
    private String registration_date;

    public StudentProgramDetailsDTO() {
    }

    public StudentProgramDetailsDTO(String st_id, String course_id, String payment, String registration_date) {
        this.st_id = st_id;
        this.course_id = course_id;
        this.payment = payment;
        this.registration_date = registration_date;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
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

    @Override
    public String toString() {
        return "StudentCourseDetailsDTO{" +
                "st_id='" + st_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", payment='" + payment + '\'' +
                ", registration_date='" + registration_date + '\'' +
                '}';
    }
}
