package Library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.*;
import java.io.IOException;
import java.sql.*;

import static Library.Configs.*;



public class Controller {

    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement pstmt_admin, pstmt_lib, pstmt_student;
    private static String window_loc = null;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Button btn_signIn;

    @FXML
    private TextField ID_signIn;

    @FXML
    private PasswordField pass_signIn;

    @FXML
    private Label error_field;
    //*********ТУТ СВОЙ ПАРОЛЬ И ЮЗЕРНЕЙМ ПОМЕНЯТЬ НЕ ЗАБУДЬТЕ В Configs class**********
    public void createConnection(){
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost +":"+dbPort+"/"+dbPath,
                    dbUser, dbPass);
            stmt = conn.createStatement();
            pstmt_admin = conn.prepareStatement("SELECT ID AND Password FROM admins WHERE ID= ? AND Password=?");
            pstmt_lib = conn.prepareStatement("SELECT ID AND Password FROM librarians WHERE ID= ? AND Password=?");
            pstmt_student = conn.prepareStatement("SELECT ID AND Password FROM students WHERE ID= ? AND Password=?");
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void signIn_action(ActionEvent event) {
        try {

            signInAs();
            if(window_loc!=null) {
                Parent newWindow = FXMLLoader.load(getClass().getResource(window_loc));
                Scene newScene = new Scene(newWindow);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            }

        }catch(IOException ex){
            System.err.println(ex);
        }
    }



    static String getID;

    private void signInAs() throws IOException {
        createConnection();
        String signIn_ID = ID_signIn.getText().trim();
        String signIn_pass = pass_signIn.getText().trim();

        if(!signIn_ID.isEmpty() && !signIn_pass.isEmpty()){
            try{
                pstmt_admin.setString(1,ID_signIn.getText().trim());
                pstmt_admin.setString(2,pass_signIn.getText().trim());
                ResultSet result_admin = pstmt_admin.executeQuery();

                pstmt_lib.setString(1,ID_signIn.getText().trim());
                pstmt_lib.setString(2,pass_signIn.getText().trim());
                ResultSet result_lib = pstmt_lib.executeQuery();

                pstmt_student.setString(1,ID_signIn.getText().trim());
                pstmt_student.setString(2,pass_signIn.getText().trim());
                ResultSet result_student = pstmt_student.executeQuery();

                if(result_admin.next()){
                   window_loc = "Admin.fxml";

                }
                else if(result_lib.next()){
                    window_loc = "Librarian.fxml";
                }
                else if(result_student.next()){
                    window_loc = "Student.fxml";
                    getID = signIn_ID;

                }
                else{
                    error_field.setText("Please enter a valid user");
                }

            }catch (SQLException ex){
                System.err.println(ex);
            }
        }
        else{
            error_field.setText("Please fill all fields");
        }

    }

}
