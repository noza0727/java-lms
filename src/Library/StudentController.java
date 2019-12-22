package Library;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Library.Configs.*;

public class StudentController implements  Initializable{
    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;
    private static ResultSet rs2;
    private ResultSet rs3;
    private static String window_loc = null;
    public ToggleGroup usersGroup;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label name_show;
    @FXML
    private Label id_show;
    @FXML
    private String f;
    @FXML
    private String l;

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

    ObservableList<String> mybooks;
    ObservableList<BookDetails> books;

    @FXML
    private TableColumn<BookDetails, String> colid, coltitle,colauthor,colstatus,colgenre;

    @FXML
    private TableColumn<BookDetails, Integer> colyear;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_show.setText(Controller.getID);
        try {
            rs=stmt.executeQuery("select FirstName , LastName from students where ID = '"+Controller.getID+"'");
            f = rs.getString("FirstName");
            l = rs.getString("LastName");
            name_show.setText(f+"  "+l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String b1,b2,b3,b4,b5;

        /*try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT book1, book2, book3, book4, book5 FROM students where ID ='"+Controller.getID+"'");
            b1 = rs.getString("book1");
            b2 = rs.getString("book2");
            b3 = rs.getString("book3");
            b4 = rs.getString("book4");
            b5 = rs.getString("book5");
            mybooks.add(b1);
            mybooks.add(b2);
            mybooks.add(b3);
            mybooks.add(b4);
            mybooks.add(b5);

            ResultSet rs2;
            for(int i = 1; i<=5; i++){
                rs2 = con.createStatement().executeQuery("select * from books where isbn ='"+b1+"'");

            }


        } catch (SQLException e) {
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE,null,e);
        }*/

    }

    public void signOut(ActionEvent event) throws IOException {
        Parent backwindow = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        Scene newScene = new Scene(backwindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
