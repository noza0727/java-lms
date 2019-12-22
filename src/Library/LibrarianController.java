package Library;

import com.sun.source.doctree.IndexTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
    private static String window_loc = null;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private AnchorPane anchorLib;

    @FXML
    private ToggleButton btn_student_lib, btn_book_lib;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createConnection();

    }






}
