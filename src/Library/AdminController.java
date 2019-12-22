package Library;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.stage.StageStyle;

import java.awt.*;
import javafx.scene.control.Label;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Library.Configs.*;


public class AdminController implements Initializable {

    private static Connection conn;
    private static Statement stmt;
    private  ResultSet rs;
    private static ResultSet rs2;
    private ResultSet rs3;
    private static String window_loc = null;
    public ToggleGroup usersGroup;
    private PreparedStatement pst;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ToggleButton btn_student, btn_lib, btn_book;


    @FXML
    private Label idField, nameField;

    @FXML
    private Button lib_cancel_add;

    @FXML
    private HBox user_box;



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


    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        //createConnection();
    }

    String userId = Controller.getAdminID;
    String fn, ln;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.setText(userId);
        createConnection();
        try {
            rs = stmt.executeQuery("select * from admins where ID ='"+userId+"'");
            while(rs.next()) {
                fn = rs.getString("FirstName");
                ln = rs.getString("LastName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       nameField.setText(fn+"  "+ln);

    }



    public void userHandler(ActionEvent e) throws IOException {
        if(e.getSource()==btn_lib){
            anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("LibAdmin.fxml")));
        }
        else if(e.getSource()==btn_student){
            anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("StudentAdmin.fxml")));
        }
        else if(e.getSource() == btn_book){
            anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("BooksAdmin.fxml")));

        }

    }

    @FXML
    private TextField edit_id_lib , edit_fname_lib, edit_lname_lib, edit_pass_lib;

    public void modifyLib(){
        String id_lib, fname_lib, lname_lib,pss_lib;
        createConnection();

    }

    public void show_profile(){

    }

    public void signOut(ActionEvent event) throws IOException {
        Parent backwindow = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        Scene newScene = new Scene(backwindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    public void adminProfile() throws IOException {

        anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("AdminProfile.fxml")));
    }
}
