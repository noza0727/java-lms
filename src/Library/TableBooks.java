package Library;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Library.Configs.*;

public class TableBooks implements Initializable {
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
    Connection con;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books");

            while (rs.next()) {
                list.add(new BookDetails(rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publish_year"),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getString("isAvailable")));
            }
        } catch (SQLException e) {
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE, null, e);
        }

        colid.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        coltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colyear.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));

        table.setItems(list);
        table.setEditable(true);

        coltitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colauthor.setCellFactory(TextFieldTableCell.forTableColumn());
        colgenre.setCellFactory(TextFieldTableCell.forTableColumn());
        colid.setCellFactory(TextFieldTableCell.forTableColumn());
        colyear.setCellFactory(TextFieldTableCell.<BookDetails,Integer>forTableColumn(new IntegerStringConverter()));
        colstatus.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void delete(ActionEvent actionEvent) {
        BookDetails selectedForDeletion = table.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled");
            alert.showAndWait();
            return;
        }
        Boolean result = deleteBook(selectedForDeletion);
        if (result) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Selected book was deleted");
            alert.showAndWait();
            list.remove(selectedForDeletion);
        }
    }

    public boolean deleteBook(BookDetails books) {
        String deleteStatement = "DELETE FROM books where ISBN = ?";
        try {
            Connection con = Database.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteStatement);
            stmt.setString(1, books.getIsbn());
            int res = stmt.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //method to edit
    public void editTitle(TableColumn.CellEditEvent editTitle) {
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();
        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);
        bookselected.setTitle(editTitle.getNewValue().toString());
        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set title='"+data2+"' where title like '"
                    +data1+"' and isbn='"+item.getIsbn()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editAuthor(TableColumn.CellEditEvent editauthor) {
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();

        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        bookselected.setAuthor(editauthor.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set author='"+data2+"' where author like '"
                    +data1+"' and isbn='"+item.getIsbn()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editGenre(TableColumn.CellEditEvent editgenre) {
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();

        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        bookselected.setGenre(editgenre.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set genre='"+data2+"' where genre like '"
                    +data1+"' and isbn='"+item.getIsbn()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editIsbn(TableColumn.CellEditEvent editisbn) {
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();

        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        bookselected.setIsbn(editisbn.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set isbn='"+data2+"' where isbn like '"
                    +data1+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editAvailable(TableColumn.CellEditEvent editavail) {
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();

        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String data1 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        bookselected.setIsAvailable(editavail.getNewValue().toString());

        String data2 = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set isAvailable='"+data2+"' where isAvailable like '"
                    +data1+"' and isbn='"+item.getIsbn()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editYearPublish(TableColumn.CellEditEvent edityear) {
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();

        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        int data1 = (Integer) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        bookselected.setYearPublished(Integer.parseInt(edityear.getNewValue().toString()));

        int data2 = (Integer) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set publish_year='"+data2+"' where publish_year like '"
                    +data1+"' and isbn='"+item.getIsbn()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteBook(){
        BookDetails bookselected = table.getSelectionModel().getSelectedItem();
        TablePosition pos =table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = table.getItems().get(row);
        try {
            if(bookselected!=null){
                PreparedStatement statement = con.prepareStatement("DELETE FROM books WHERE isbn = ?");
                statement.setString(1, item.getIsbn());
                statement.executeUpdate();
            }
            else
                System.out.println("its null awe");

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void addBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        Stage stageforadd = new Stage();
        stageforadd.setScene(new Scene((Pane) loader.load()));
        stageforadd.show();
    }

    
}




