package Library;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Library.Configs.*;

public class StudentController implements  Initializable{
    private static Connection conn;
    private static Statement stmt;
    private ResultSet rs;
    private ResultSet show;
    private String f;
    private String l;

    @FXML
    private Button closeButtonS;
    @FXML
    private Label name_show;
    @FXML
    private Label id_show;

    @FXML
    private TableView<BookDetails> myBookTable;
    @FXML
    private TableColumn<BookDetails, String> colisbn, coltitle,colauthor,colgenre;

    @FXML
    private TableColumn<BookDetails, String> coldate;

    @FXML
    private TableColumn<BookDetails, Integer> colyear;

    ObservableList<BookDetails> mycollections = FXCollections.observableArrayList();
    String y = Controller.getID;

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
        id_show.setText(y);
        try{
            rs = stmt.executeQuery("select * from students where id='"+y+"'");
            while(rs.next()){
                String f = rs.getString("firstname");
                String l = rs.getString("lastname");
                name_show.setText(f+"  "+l);
            }
            rs.close();
            show = conn.createStatement().executeQuery("SELECT * FROM "+ y.toLowerCase()+" ");
            while(show.next()) {
              mycollections.add(new BookDetails(show.getString("bookIsbn"),
                        show.getString("issueDate"),
                        show.getString("bookTitle"),
                        show.getString("bookAuthor"),
                        show.getString("bookGenre"),
                        show.getInt("bookYear")));
            }
            coltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            colyear.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
            colisbn. setCellValueFactory(new PropertyValueFactory<>("isbn"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("issueTime"));
            myBookTable.setItems(mycollections);


        }catch(SQLException e){
            System.err.println();
            e.printStackTrace();
        }

    }

    public void getReturnInfo() throws SQLException {
        int count = 0;
        Statement change = conn.createStatement();
        Statement dlt = conn.createStatement();
        BookDetails bookselected = myBookTable.getSelectionModel().getSelectedItem();
        TablePosition pos =myBookTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = myBookTable.getItems().get(row);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to return "+item.getTitle()+" ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.YES) {
            Statement returnBack = conn.createStatement();
            ResultSet ret = returnBack.executeQuery("select * from books where isbn = '"+item.getIsbn()+"'");
            while(ret.next()){
               dlt.executeUpdate("delete from "+y+" where bookIsbn = '"+item.getIsbn()+"'");
               count = ret.getInt("amount") + 1;
               change.executeUpdate("update books set amount ='"+count+"' where isbn = '"+item.getIsbn()+"'");
            }

        } else if (option.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    public void returnButton(){
        try {
            getReturnInfo();
        }catch(SQLException e){
            System.err.println();
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

    @FXML
    public void handleCloseButton(){
        Stage stage5 = (Stage) closeButtonS.getScene().getWindow();
        stage5.close();
    }

    public void calculateFine() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("fine.fxml"));
        Scene scene2 = new Scene(pane);
        scene2.setFill(Color.TRANSPARENT);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.setOnCloseRequest(e-> Platform.exit());
        stage2.show();
    }


}
