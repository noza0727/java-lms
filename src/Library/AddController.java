package Library;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField subject;
    @FXML
    private TextField author;
    @FXML
    private TextField year;
    @FXML
    private TextField isAvailable;

    private Boolean isModify = Boolean.FALSE;

    @Override public void initialize(URL url, ResourceBundle rb){ }

    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        isbn.setText(null);
        title.setText(null);
        subject.setText(null);
        author.setText(null);
        year.setText(null);
        isAvailable.setText(null);
    }

    public void save(MouseEvent mouseEvent) {
        String bookISBN = isbn.getText();
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookSubject = subject.getText();
        int bookYear = Integer.parseInt(year.getText());
        String bookAvail = isAvailable.getText();

        if(bookISBN.isEmpty() || bookAvail.isEmpty() || bookTitle.isEmpty() ||
                bookSubject.isEmpty() || bookAuthor.isEmpty() || bookYear==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled");
            alert.showAndWait();
            return;
        }

        if (isModify){
            modifyBook();
            return;
        }

        String insertion = "INSERT INTO books VALUES (" +
                "'" + bookISBN + "'," +
                "'" + bookTitle + "'," +
                "'" + bookAuthor + "'," +
                "'" + bookSubject + "'," +
                "'" + bookYear + "'," +
                "'" + bookAvail + "'" +
                ")";
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(insertion);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saved successfully");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifyBook() {
        BookDetails books = new BookDetails( title.getText(), author.getText(),Integer.parseInt(year.getText()),subject.getText(),isbn.getText(),isAvailable.getText());
        if(modify(books)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Details are modified");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nothing is changed");
            alert.showAndWait();
        }
    }

    public void inflate(BookDetails books){
        String y = Integer.toString(books.getYearPublished());
        isbn.setText(books.getIsbn());
        title.setText(books.getTitle());
        author.setText(books.getAuthor());
        subject.setText(books.getGenre());
        year.setText(y);
        isAvailable.setText(books.getIsAvailable());
        isModify = Boolean.TRUE;
        isbn.setEditable(false);
    }

    public Boolean modify(BookDetails books){
        String modify = "UPDATE books SET Title = ?, Author = ?, Subject = ?, yearPublished = ?, isAvailable = ? WHERE ISBN = ?";
        try {
            Connection con = Database.getConnection();
            PreparedStatement stmt = con.prepareStatement(modify);
            stmt.setString(1, books.getTitle());
            stmt.setString(2, books.getAuthor());
            stmt.setString(3, books.getGenre());
            stmt.setInt(4, books.getYearPublished());
            stmt.setString(5, books.getIsAvailable());
            stmt.setString(6, books.getIsbn());
            int res = stmt.executeUpdate();
            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
