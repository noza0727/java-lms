package Library;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableBooks implements Initializable{
    @FXML
    private TableView<BookDetails> table;
    @FXML
    private TableColumn<BookDetails, String> colid;
    @FXML
    private TableColumn<BookDetails, String> coltitle;
    @FXML
    private TableColumn<BookDetails, String> colauthor;
    @FXML
    private TableColumn<BookDetails, String> colgenre;
    @FXML
    private TableColumn<BookDetails, Integer> colyear;
    @FXML
    private TableColumn<BookDetails, String> colstatus;

    ObservableList<BookDetails> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books");

            while(rs.next()) {
                list.add(new BookDetails(rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publish_year"),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getString("isAvailable")));
            }
        } catch (SQLException e) {
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE,null,e);
        }

        colid.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        coltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colyear.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));

        table.setItems(list);
    }

    public void delete(ActionEvent actionEvent) {
        BookDetails selectedForDeletion = table.getSelectionModel().getSelectedItem();
        if(selectedForDeletion == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled");
            alert.showAndWait();
            return;
        }
        Boolean result = deleteBook(selectedForDeletion);
        if(result){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Selected book was deleted");
            alert.showAndWait();
            list.remove(selectedForDeletion);
        }
    }

    public boolean deleteBook (BookDetails books){
        String deleteStatement = "DELETE FROM books where ISBN = ?";
        try {
            Connection con = Database.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteStatement);
            stmt.setString(1, books.getIsbn());
            int res = stmt.executeUpdate();
            if(res == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
