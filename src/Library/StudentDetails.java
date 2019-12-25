package Library;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class StudentDetails  {
    private SimpleStringProperty studentID;
    private SimpleStringProperty studentFname;
    private SimpleStringProperty studentLname;
    private SimpleStringProperty studentYear;
    private SimpleStringProperty studentDepartment;

    public StudentDetails(SimpleStringProperty studentID, SimpleStringProperty studentFname, SimpleStringProperty studentLname, SimpleStringProperty studentYear, SimpleStringProperty studentDepartment) {
        this.studentID = studentID;
        this.studentFname = studentFname;
        this.studentLname = studentLname;
        this.studentYear = studentYear;
        this.studentDepartment = studentDepartment;
    }

    public StudentDetails(String id, String firstName, String lastName, String year, String department) {
        this.studentID = new SimpleStringProperty(id);
        this.studentFname = new SimpleStringProperty(firstName);
        this.studentLname =new SimpleStringProperty(lastName);;
        this.studentYear = new SimpleStringProperty(year);
        this.studentDepartment = new SimpleStringProperty(department);
    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }

    public void setStudentFname(String studentFname) {
        this.studentFname.set(studentFname);
    }

    public void setStudentLname(String studentLname) {
        this.studentLname.set(studentLname);
    }

    public void setStudentYear(String studentYear) {
        this.studentYear.set(studentYear);
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment.set(studentDepartment);
    }

    public String getStudentID() {
        return studentID.get();
    }

    public SimpleStringProperty studentIDProperty() {
        return studentID;
    }

    public String getStudentFname() {
        return studentFname.get();
    }

    public SimpleStringProperty studentFnameProperty() {
        return studentFname;
    }

    public String getStudentLname() {
        return studentLname.get();
    }

    public SimpleStringProperty studentLnameProperty() {
        return studentLname;
    }

    public String getStudentYear() {
        return studentYear.get();
    }

    public SimpleStringProperty studentYearProperty() {
        return studentYear;
    }

    public String getStudentDepartment() {
        return studentDepartment.get();
    }

    public SimpleStringProperty studentDepartmentProperty() {
        return studentDepartment;
    }
}
