package Library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Library.Configs.*;

public class AddLibrarianController implements Initializable {
    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;

    @FXML
    private Button closeButton;

    @FXML
    private Label add_error;

    @FXML
    private TextField lib_ID_add, lib_pass_add, lib_Fname_add, lib_Lname_add;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public void clearAdd(){
        lib_ID_add.clear();
        lib_pass_add.clear();
        lib_Lname_add.clear();
        lib_Fname_add.clear();
    }

    public void addLib(ActionEvent event) {
        createConnection();
        if (lib_ID_add.getText().isEmpty() || lib_Fname_add.getText().isEmpty() || lib_Lname_add.getText().isEmpty()||lib_pass_add.getText().isEmpty()) {
            add_error.setText("Please fill all the fields");
        }
        else{
        try {

            String sqlinsert = "INSERT INTO `librarians`(`ID`, `FirstName`, `LastName`) VALUES ('"
                    + lib_ID_add.getText().trim() + "','"
                    + lib_Fname_add.getText().trim() + "','"
                    + lib_Lname_add.getText().trim() + "','"
                    + lib_pass_add.getText().trim() + "')";

            stmt.executeUpdate(sqlinsert);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        }
    }


    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        //createConnection();
    }
}
