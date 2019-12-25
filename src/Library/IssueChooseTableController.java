package Library;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.rmi.StubNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;


public class IssueChooseTableController implements Initializable {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    Connection con;

    @FXML
    private  Button closeButton;

    @FXML
    private TableView<BookDetails> bookTable;
    @FXML
    private TableColumn<BookDetails, String> titleCol;
    @FXML
    private TableColumn<BookDetails, String> authorCol;
    @FXML
    private TableColumn<BookDetails, String> isbnCol;
    @FXML
    private TableColumn<BookDetails, String> genreCol;
    @FXML
    private TableColumn<BookDetails, Integer> amountCol;

    @FXML
    private TableView<StudentDetails> studentTable;
    @FXML
    private TableColumn<StudentDetails, String> idCol;
    @FXML
    private TableColumn<BookDetails, String> fnameCol;
    @FXML
    private TableColumn<BookDetails, String> lnameCol;
    @FXML
    private TableColumn<BookDetails, String> yearCol;
    @FXML
    private TableColumn<BookDetails, String> departmentCol;

    @FXML
    private TextField bookTitle, bookAuthor, bookIsbn, bookAmount;
    @FXML
    private TextField idInfo, nameInfo, yearInfo, departmentInfo;

    ObservableList<BookDetails> book_list = FXCollections.observableArrayList();
    ObservableList<StudentDetails> student_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books");

            while (rs.next()) {
                book_list.add(new BookDetails(rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publish_year"),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("amount")));
            }
        } catch (SQLException e) {
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE, null, e);
        }

        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        bookTable.setItems(book_list);

        bookTable.setRowFactory( tv -> {
            TableRow<BookDetails> row0 = new TableRow<>();
            row0.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row0.isEmpty()) ) {
                    showBookInfo();
                }
            });
            return row0 ;
        });

        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM students");

            while (rs.next()) {
                StudentDetails student = new StudentDetails(rs.getString("id"),
                        rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("year"), rs.getString("department"));
                student_list.add(student);
            }
        } catch (SQLException e) {
            Logger.getLogger(TableBooks.class.getName()).log(Level.SEVERE, null, e);
        }

            idCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
            fnameCol.setCellValueFactory(new PropertyValueFactory<>("studentFname"));
            lnameCol.setCellValueFactory(new PropertyValueFactory<>("studentLname"));
            yearCol.setCellValueFactory(new PropertyValueFactory<>("studentYear"));
            departmentCol.setCellValueFactory(new PropertyValueFactory<>("studentDepartment"));
            studentTable.setItems(student_list);

        studentTable.setRowFactory( tv -> {
            TableRow<StudentDetails> row0 = new TableRow<>();
            row0.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row0.isEmpty()) ) {
                    showStudentInfo();
                }
            });
            return row0 ;
        });
    }

    public void showBookInfo(){
        BookDetails bookselected = bookTable.getSelectionModel().getSelectedItem();
        TablePosition pos =bookTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        BookDetails item = bookTable.getItems().get(row);
        if(bookselected!=null){
            bookTitle.setText(item.getTitle());
            bookAuthor.setText(item.getAuthor());
            bookIsbn.setText(item.getIsbn());
            bookAmount.setText(item.getAmount()+" ");

        }

    }
    public void showStudentInfo(){
        StudentDetails studentselected = studentTable.getSelectionModel().getSelectedItem();
        TablePosition pos =studentTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        StudentDetails item = studentTable.getItems().get(row);
        if(studentselected!=null){
            idInfo.setText(item.getStudentID());
            nameInfo.setText(item.getStudentFname()+"  "+item.getStudentLname());
            yearInfo.setText(item.getStudentYear());
            departmentInfo.setText(item.getStudentDepartment());
        }

    }

    public void issueSelected(){
        String gotID = idInfo.getText().toUpperCase();
        String gotIsbn = bookIsbn.getText();
        int cont = 1, contIfavailable = 0;
        try {
            con = Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet check = stm.executeQuery("select amount from books where isbn ='"+gotIsbn+"'");
            while(check.next()) {
                System.out.println("in checking for books");
                try{
                    if (check.getInt("amount") > 0) {
                        contIfavailable = 1;
                    System.out.println("amount is greater than 0, its available");
                    String creatTable = "create table if not exists " + gotID + " (" +
                            "bookIsbn VARCHAR(100) default 'none' PRIMARY KEY, " +
                            "issueDate VARCHAR(100) default '00.00.00', " +
                            "bookTitle VARCHAR(100) default 'none'," +
                            "bookAuthor VARCHAR(100) default 'none'," +
                            "bookGenre VARCHAR(100) default 'none'," +
                            "bookYear int default 0)";
                    System.out.println("creating a table");
                    stm.executeUpdate(creatTable);
                    System.out.println("table is created");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("The book is not available. Please choose another book");
                        alert.showAndWait();
                    }
                    check.close();
                    break;
                }catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            }

            if(contIfavailable == 1){
            try{
                Statement s2 = con.createStatement();
            ResultSet set;
            set = s2.executeQuery("select bookIsbn from " + gotID);
            while (set.next()) {
                System.out.println("Inside while: student has this book already");
               try {
                   if (set.getString("bookIsbn").equals(gotIsbn)) {
                       cont = 0;
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setHeaderText(null);
                       alert.setContentText("Student " + gotID + " already has this book");
                       alert.showAndWait();
                       set.close();
                       break;
                   }
                   else {
                       continue;
                   }
               }catch (SQLException e) {
                   e.printStackTrace();
               }
            }

            } catch (SQLException e) {
               e.printStackTrace();
            }
           if(cont ==1) {
               try {
                   String t = "none" ,a = "none" ,g = "none";
                   int y = 0;
                   Statement getDataFromBooks = con.createStatement();
                   ResultSet collectData = getDataFromBooks.executeQuery("select * from books where isbn = '"+gotIsbn+"'");
                   while(collectData.next()){
                       t = collectData.getString("title");
                       a = collectData.getString("author");
                       g = collectData.getString("genre");
                       y = collectData.getInt("publish_year");
                   }
                   Statement s3 = con.createStatement();
                   String issue = "insert into " + gotID + " (bookIsbn, issueDate, bookTitle, bookAuthor, bookGenre, bookYear) " +
                           "values ('" + gotIsbn + "','" + formatter.format(date) + "','"+t+"','"+a+"','"+g+"','"+y+"')";
                   s3.executeUpdate(issue);
                   System.out.println(gotID+"book is inserted " + formatter.format(date));
                   int count =0;
                   Statement s4 = con.createStatement();
                   Statement s5 = con.createStatement();
                   ResultSet res;
                   res = s4.executeQuery("select amount from books where isbn = '" + gotIsbn + "'");
                   while(res.next()) {
                       try {
                           count = res.getInt("amount") - 1;
                           s5.executeUpdate("update books set amount = '" + count + "' where isbn ='" + gotIsbn + "'");
                           System.out.println("amount is decreased");
                           res.close();
                           break;
                       }catch (SQLException e) {
                           e.printStackTrace();
                       }
                   }
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText(null);
                   alert.setContentText("The book " + gotIsbn + " is successfully issued to " + gotID);
                   alert.showAndWait();

               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
           }
    }catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong please try again");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void fineSelected() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("fine.fxml"));
        Scene scene2 = new Scene(pane);
        scene2.setFill(Color.TRANSPARENT);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.setOnCloseRequest(e-> Platform.exit());
        stage2.show();
    }

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
