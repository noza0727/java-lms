package Library;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Library.Configs.*;

public class LibrarianTable implements Initializable{
    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;

    @FXML
    private TextField lib_ID_add, lib_pass_add, lib_Fname_add, lib_Lname_add;
    @FXML
    private TableView<LibrarianDetails> tableLibrarian;
    @FXML
    private TableColumn<LibrarianDetails, String> col_id_lib;
    @FXML
    private TableColumn<LibrarianDetails, String> col_fname_lib;
    @FXML
    private TableColumn<LibrarianDetails, String> col_lname_lib;
    @FXML
    private TableColumn<LibrarianDetails, CheckBox> col_select_lib;

    @FXML
    private TextField field_search_lib;
    ObservableList<LibrarianDetails> list_lib= FXCollections.observableArrayList();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost +":"+dbPort+"/"+dbPath,
                    dbUser, dbPass);

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM librarians");
            int i = 0;
            while(rs.next()) {
                //CheckBox ch = new CheckBox(""+(i++));
                list_lib.add(new LibrarianDetails(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        }catch(SQLException e){
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE,null,e);
        }

        col_id_lib.setCellValueFactory(new PropertyValueFactory<LibrarianDetails, String>("id"));
        col_fname_lib.setCellValueFactory(new PropertyValueFactory<LibrarianDetails, String>("firstname"));
        col_lname_lib.setCellValueFactory(new PropertyValueFactory<LibrarianDetails, String>("lastname"));
        //col_select_lib.setCellValueFactory(new PropertyValueFactory<LibrarianDetails, CheckBox>("checkBox"));
        tableLibrarian.setItems(list_lib);

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

    public void addLibrarian(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddLibrarian.fxml"));
        Scene scene2 = new Scene(pane);
        scene2.setFill(Color.TRANSPARENT);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.setOnCloseRequest(e-> Platform.exit());
        stage2.show();




    }




    public void setBtn_search_lib(){
        String data_lib = field_search_lib.getText();
        createConnection();
        String search = "select * from librarians where ID = '"+
                data_lib+"' OR FirstName like '"+
                data_lib + "' OR LastName like '"+
                data_lib +"'";

    }

    public void clearAdd(){
        lib_ID_add.clear();
        lib_pass_add.clear();
        lib_Lname_add.clear();
        lib_Fname_add.clear();
    }
}
