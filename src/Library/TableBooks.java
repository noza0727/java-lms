package Library;


import com.sun.glass.ui.Window;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TableColumn<BookDetails,Integer> colstatus;
    @FXML
    private Button closeButton;
    @FXML
    private TextField searchField, issuestudentID ;
    @FXML
    private VBox vBox;
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
                        rs.getInt("amount")));
            }
        } catch (SQLException e) {
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE, null, e);
        }

        colid.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        coltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colyear.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("amount"));
        table.setItems(list);

        table.setEditable(true);

        coltitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colauthor.setCellFactory(TextFieldTableCell.forTableColumn());
        colgenre.setCellFactory(TextFieldTableCell.forTableColumn());
        colid.setCellFactory(TextFieldTableCell.forTableColumn());
        colyear.setCellFactory(TextFieldTableCell.<BookDetails,Integer>forTableColumn(new IntegerStringConverter()));
        colstatus.setCellFactory(TextFieldTableCell.<BookDetails,Integer>forTableColumn(new IntegerStringConverter()));

        FilteredList<BookDetails> filteredList = new FilteredList<>(list, e -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(books -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (books.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (books.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (books.getGenre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
            SortedList<BookDetails>sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
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

        int data1 = (Integer) col.getCellObservableValue(item).getValue();
        System.out.println(data1);

        bookselected.setAmount(Integer.parseInt(editavail.getNewValue().toString()));

        int data2 = (Integer) col.getCellObservableValue(item).getValue();
        System.out.println(data2);

        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE books set amount='"+data2+"' where isbn='"+item.getIsbn()+"'");
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

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void issueAccess() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IssueChooseTable.fxml"));
        Stage stageIssue = new Stage();
        stageIssue.setScene(new Scene((Pane) loader.load()));
        stageIssue.show();
    }
}





