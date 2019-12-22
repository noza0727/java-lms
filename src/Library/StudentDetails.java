package Library;

import javafx.scene.control.CheckBox;

public class StudentDetails  {
    private String studentID;
    private String studentFname;
    private String studentLname;
    private String studentYear;
    private String studentDepartment;

    public StudentDetails(String studentID, String studentFname, String studentLname, String studetnYear, String studentDepartment) {
        this.studentID = studentID;
        this.studentFname = studentFname;
        this.studentLname = studentLname;
        this.studentYear = studetnYear;
        this.studentDepartment = studentDepartment;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentFname() {
        return studentFname;
    }

    public String getStudentLname() {
        return studentLname;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentFname(String studentFname) {
        this.studentFname = studentFname;
    }

    public void setStudentLname(String studentLname) {
        this.studentLname = studentLname;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }
}
