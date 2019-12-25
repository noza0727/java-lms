package Library;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Library.Configs.*;

public class MyBooks {
    private String bookIsbn;
    private String issueDate;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private int bookYear;

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public int getBookYear() {
        return bookYear;
    }

    public MyBooks(String bookIsbn, String issueDate, String bookTitle, String bookAuthor, String bookGenre, int bookYear) {
        this.bookIsbn = bookIsbn;
        this.issueDate = issueDate;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.bookYear = bookYear;
    }
}
