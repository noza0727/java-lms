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
import javafx.scene.control.TableView;
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

    private String f;
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
    private TableView myBookTable;
    @FXML
    private TableColumn<BookDetails, String> colid, coltitle,colauthor,colstatus,colgenre;

    @FXML
    private TableColumn<BookDetails, Integer> colyear;
    String y = Controller.getID;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_show.setText(y);
        try {
            rs3 = stmt.executeQuery("select * from students where ID='" + y + "'");
            while (rs3.next()) {
                String f = rs3.getString("FirstName");
                String l = rs3.getString("LastName");
                name_show.setText(f + "  " + l);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void signOut(ActionEvent event) throws IOException {
        Parent backwindow = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        Scene newScene = new Scene(backwindow);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
