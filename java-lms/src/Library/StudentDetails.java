package Library;

import javafx.scene.control.CheckBox;

public class StudentDetails  {
    private String id;
    private String fname;
    private String lname;
    private String year;
    private String department;

    public StudentDetails(String id, String fname, String lname, String year, String department) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.year = year;
        this.department = department;
    }
}
