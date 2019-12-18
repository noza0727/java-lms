package Library;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static Library.Configs.*;


public class AdminController implements Initializable {

    private static Connection conn;
    private static Statement stmt;
    private static String window_loc = null;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Pane viewPane_lib, addPane_lib, deletePane_lib, modifyPane_lib;

    @FXML
    private Button add_lib, view_lib, modify_lib,delete_lib, logOut_admin;

    @FXML
    private TextField lib_ID_add, lib_pass_add, lib_Fname_add, lib_Lname_add;

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createConnection();

    }

    public void addLibrarian(){
        createConnection();
        try {
            String sqlinsert = "INSERT INTO `librarians`(`ID`, `FirstName`, `LastName`, `Password`) VALUES ('"
                    + lib_ID_add.getText().trim() + "','"
                    + lib_Fname_add.getText().trim() + "','"
                    + lib_Lname_add.getText().trim() + "','"
                    + lib_pass_add.getText().trim() + "')";
            stmt.executeUpdate(sqlinsert);
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void libButtonHandler(ActionEvent event){
        if(event.getSource()==view_lib){
            viewPane_lib.toFront();
            /*addPane_lib.setVisible(false);
            modifyPane_lib.setVisible(false);
            deletePane_lib.setVisible(false);
            settingsPane.setVisible(false);
            logOutPane.setVisible(false);
            addPane_lib.setDisable(false);
            modifyPane_lib.setDisable(false);
            deletePane_lib.setDisable(false);
            settingsPane.setDisable(false);
            logOutPane.setDisable(false);

             */

        }
        else if(event.getSource() == add_lib){
            addPane_lib.toFront();

            /*viewPane_lib.setVisible(false);
            modifyPane_lib.setVisible(false);
            deletePane_lib.setVisible(false);
            settingsPane.setVisible(false);
            logOutPane.setVisible(false);

            viewPane_lib.setDisable(false);
            modifyPane_lib.setDisable(false);
            deletePane_lib.setDisable(false);
            settingsPane.setDisable(false);
            logOutPane.setDisable(false);

             */
        }
        else if(event.getSource() == delete_lib){
            deletePane_lib.toFront();
            /*viewPane_lib.setVisible(false);
            modifyPane_lib.setVisible(false);
            addPane_lib.setVisible(false);
            settingsPane.setVisible(false);
            logOutPane.setVisible(false);

            viewPane_lib.setDisable(false);
            modifyPane_lib.setDisable(false);
            addPane_lib.setDisable(false);
            settingsPane.setDisable(false);
            logOutPane.setDisable(false);

             */
        }
        else if(event.getSource() == modify_lib){
            modifyPane_lib.toFront();viewPane_lib.setVisible(false);
            /*addPane_lib.setVisible(false);
            deletePane_lib.setVisible(false);
            settingsPane.setVisible(false);
            logOutPane.setVisible(false);

            viewPane_lib.setDisable(false);
            addPane_lib.setDisable(false);
            deletePane_lib.setDisable(false);
            settingsPane.setDisable(false);
            logOutPane.setDisable(false);

             */

        }

        else if(event.getSource()==logOut_admin){


        }
        else {
            viewPane_lib.toFront();
            /*addPane_lib.setVisible(false);
            modifyPane_lib.setVisible(false);
            deletePane_lib.setVisible(false);
            settingsPane.setVisible(false);
            logOutPane.setVisible(false);
            addPane_lib.setDisable(false);
            modifyPane_lib.setDisable(false);
            deletePane_lib.setDisable(false);
            settingsPane.setDisable(false);
            logOutPane.setDisable(false);

             */
        }
    }


}
