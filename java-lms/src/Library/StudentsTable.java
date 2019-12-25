package Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.spi.InitialContextFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Library.Configs.*;

public class StudentsTable implements Initializable {

    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;


    @FXML
    private TableView<StudentDetails> tableStudent;

    @FXML
    private TableColumn<StudentDetails, String> col_id_student;
    @FXML
    private TableColumn<StudentDetails, String> col_fname_student;
    @FXML
    private TableColumn<StudentDetails, String> col_lname_student;
    @FXML
    private TableColumn<StudentDetails, String> col_year_student;
    @FXML
    private TableColumn<StudentDetails, String> col_department;
    @FXML
    private TableColumn<StudentDetails, CheckBox> col_select_student;

    private ObservableList<StudentDetails> list_student = FXCollections.observableArrayList();;

    public void createConnection(){
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost +":"+dbPort+"/"+dbPath,
                    dbUser, dbPass);
            stmt = conn.createStatement();

        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            createConnection();
            String sqlData = "SELECT `ID`, `FirstName`, `LastName`, `Year`, `Department` FROM `students`";
            rs = conn.createStatement().executeQuery(sqlData);
            int i = 0;
            while (rs.next()) {
                CheckBox ch2 = new CheckBox("" + (i++));
                StudentDetails student = new StudentDetails(rs.getString("ID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Year"),
                        rs.getString("Department"));
                list_student.add(student);

            }

            col_id_student.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_fname_student.setCellValueFactory(new PropertyValueFactory<>("fName"));
            col_lname_student.setCellValueFactory(new PropertyValueFactory<>("lName"));
            col_year_student.setCellValueFactory(new PropertyValueFactory<>("year"));
            col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
            //col_select_student.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
            tableStudent.setItems(list_student);

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
