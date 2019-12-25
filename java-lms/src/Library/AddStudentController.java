package Library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Library.Configs.*;

public class AddStudentController implements Initializable {
    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;

    @FXML
    private TextField student_ID_add, student_pass_add, student_Fname_add, student_Lname_add;

    @FXML
    private ComboBox depcombo , yearcombo;

    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        depcombo.getItems().add("SOCIE");
        depcombo.getItems().add("SOL");
        yearcombo.getItems().add("Freshman");
        yearcombo.getItems().add("Sophomore");
        yearcombo.getItems().add("Junior");
        yearcombo.getItems().add("Senior");
    }


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

    public void addStudent(){
        createConnection();
        try {
            String sqlInsert = "INSERT INTO `students`(`ID`, `FirstName`, `LastName`, `Password`,`Year`, `Department`) VALUES ('"
                    + student_ID_add.getText().trim() + "','"
                    + student_Fname_add.getText().trim() + "','"
                    + student_Lname_add.getText().trim() + "','"
                    + student_pass_add.getText().trim() + "','"
                    + yearcombo.getValue().toString()+"','"
                    + depcombo.getValue().toString()+"')";
            stmt.executeUpdate(sqlInsert);
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void clearAdd(){
        student_ID_add.clear();
        student_pass_add.clear();
        student_Lname_add.clear();
        student_Fname_add.clear();
    }



}
