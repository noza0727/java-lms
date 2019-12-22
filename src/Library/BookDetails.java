package Library;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookDetails {
    SimpleStringProperty isbn;
    SimpleStringProperty title;
    SimpleStringProperty author;
    SimpleStringProperty genre;
    SimpleIntegerProperty yearPublished;
    SimpleStringProperty isAvailable;

    public BookDetails(String title, String author, int publish_year, String genre, String isbn, String isAvailable) {
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty( author);
        this.genre = new SimpleStringProperty(genre);
        this.yearPublished = new SimpleIntegerProperty(publish_year);
        this.isAvailable = new SimpleStringProperty(isAvailable);
    }

    public void setIsbn(String isbn) {

        this.isbn = new SimpleStringProperty(isbn);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public String getGenre() {
        return genre.get();
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public int getYearPublished() {
        return yearPublished.get();
    }

    public SimpleIntegerProperty yearPublishedProperty() {
        return yearPublished;
    }

    public String getIsAvailable() {
        return isAvailable.get();
    }

    public SimpleStringProperty isAvailableProperty() {
        return isAvailable;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished.set(yearPublished);
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable.set(isAvailable);
    }
}
