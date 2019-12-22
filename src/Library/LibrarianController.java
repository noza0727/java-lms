package Library;

import com.sun.source.doctree.IndexTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Library.Configs.*;

public class LibrarianController implements Initializable {

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static String window_loc = null;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private AnchorPane anchorLib;

    @FXML
    private ToggleButton btn_student_lib, btn_book_lib;

    @FXML
    private TextField studentID, bookIsbn;
    @FXML
    private Label libNameField, libIdField;

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TextField lib_ID_add, lib_pass_add, lib_Fname_add, lib_Lname_add;

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



    public void userHandlerLib(ActionEvent e) throws IOException {
        if(e.getSource()==btn_student_lib){
            anchorLib.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("StudentAdmin.fxml")));
        }
        else if(e.getSource()==btn_book_lib){
            anchorLib.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("BooksAdmin.fxml")));
        }

    }

    private String libuserID = Controller.getLibID;
    private String fn,ln;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        libIdField.setText(libuserID);
        createConnection();
        try {
            rs = stmt.executeQuery("select * from librarians where id ='"+libuserID+"'");
            while(rs.next()) {
                fn = rs.getString("FirstName");
                ln = rs.getString("LastName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        libNameField.setText(fn+"  "+ln);
    }


    public void signOut(ActionEvent event) throws IOException {
        Parent backwindow = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        Scene newScene = new Scene(backwindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    public void loadIssue(){
        String studentIssueID = studentID.getText();
        String bookIssueisbn = bookIsbn.getId();


    }
    public void libProfile() throws IOException {

        anchorLib.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("LibProfile.fxml")));
    }

}
