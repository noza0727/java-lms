package Library;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Library.Configs.*;


public class AdminController implements Initializable {

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static String window_loc = null;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ToggleButton btn_student, btn_lib, btn_book;

    @FXML
    private TextField student_ID_add, student_pass_add, student_Fname_add, student_Lname_add;

    @FXML
    private ToggleButton add_lib, view_lib, modify_lib,delete_lib, logOut_admin;

    @FXML
    private TextField lib_ID_add, lib_pass_add, lib_Fname_add, lib_Lname_add;

    @FXML
    private Button lib_cancel_add;

    @FXML
    private HBox user_box;

    @FXML
    private TableView<LibrarianDetails> tableLibrarian;

    @FXML
    private TableColumn<LibrarianDetails, String> ID_column;
    @FXML
    private TableColumn<LibrarianDetails, String> fName_column;
    @FXML
    private TableColumn<LibrarianDetails, String> lName_column;
    @FXML
    private TableColumn modify_column;

    private ObservableList<LibrarianDetails> list;

    public void librarianTableView() throws SQLException {
        createConnection();
        list = FXCollections.observableArrayList();
        String sqlData = "select * from librarians";
        rs = conn.createStatement().executeQuery(sqlData);
        while(rs.next()){
            LibrarianDetails librarian = new LibrarianDetails();
            librarian.setID(rs.getString("ID_column"));

        }
    }

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        createConnection();
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

    public static int option=0;
    public static int user=0;
    public void optionHandler(ActionEvent event) throws IOException {
        if(event.getSource() == view_lib) {
           option =1;
           windowHandler();
        }
        else if(event.getSource() == add_lib){
           option =2;
            windowHandler();
        }
        else if(event.getSource() == modify_lib){
option=3;
            windowHandler();
        }
        else if(event.getSource() == delete_lib){
option=4;
            windowHandler();
        }
        else if(event.getSource() == logOut_admin){
option=5;
            windowHandler();
        }

    }

    public void userHandler(ActionEvent e) throws IOException {
        if(e.getSource()==btn_lib){
            user =1;
            windowHandler();
        }
        else if(e.getSource()==btn_student){
            user=2;
            windowHandler();
        }
        else if(e.getSource() == btn_book){
             user=3;
            windowHandler();
        }

    }

     public void windowHandler() throws IOException {
         if (option == 1 && user == 1) {  //librarian view

         }
         else if(option == 2 && user ==1 )  {// librarian add
             anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("AddLibrarian.fxml")));
         }
         else if(option == 3 && user ==1 )  {// librarian modify

         }
         else if(option == 4 && user ==1 )  {// librarian delete

         }
         else if(option == 1 && user ==2 )  {// student view

         }
         else if(option == 2 && user ==2 )  {// student add
             anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("AddStudent.fxml")));
         }
         else if(option == 3 && user ==2 )  {// student modify

         }
         else if(option == 4 && user ==2 )  {// student delete

         }
         else if(option == 1 && user ==3 )  {// book view

         }
         else if(option == 2 && user ==3 )  {// book add

         }
         else if(option == 3 && user ==3 )  {// book modify

         }
         else if(option == 4 && user ==3 )  {// book delete

         }

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

    public void addStudent(){
        createConnection();
        try {
            String sqlInsert = "INSERT INTO `students`(`ID`, `FirstName`, `LastName`, `Password`) VALUES ('"
                    + student_ID_add.getText().trim() + "','"
                    + student_Fname_add.getText().trim() + "','"
                    + student_Lname_add.getText().trim() + "','"
                    + student_pass_add.getText().trim() + "')";
            stmt.executeUpdate(sqlInsert);
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void cancelAdd() throws IOException{
        anchorPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("DefaultPane.fxml")));
    }

    public void clearAdd(){
        lib_ID_add.clear();
        lib_pass_add.clear();
        lib_Lname_add.clear();
        lib_Fname_add.clear();
    }


}
