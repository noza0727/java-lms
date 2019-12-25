package Library;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookDetails {
    String isbn;
    String title;
    String author;
    String genre;
    int yearPublished;
    String isAvailable;

    public BookDetails(String title, String author, int publish_year, String genre, String isbn, String isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = publish_year;
        this.isAvailable = isAvailable;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getIsAvailable() {
        return isAvailable;
    }
}
