package Library;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookDetails {
    private SimpleStringProperty isbn;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty genre;
    private SimpleIntegerProperty yearPublished;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty issueTime;



    public void setIssueTime(String issueTime) {
        this.issueTime.set(issueTime);
    }

    public BookDetails(String isbn, String issueDate, String title, String author, String genre, int publish_year) {
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.yearPublished = new SimpleIntegerProperty(publish_year);
        this.issueTime = new SimpleStringProperty(issueDate);
    }


    public String getIssueTime() {
        return issueTime.get();
    }

    public SimpleStringProperty issueTimeProperty() {
        return issueTime;
    }

    public BookDetails(String title, String author, int publish_year, String genre, String isbn, int amount) {
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty( author);
        this.genre = new SimpleStringProperty(genre);
        this.yearPublished = new SimpleIntegerProperty(publish_year);
        this.amount = new SimpleIntegerProperty(amount);
    }

    public BookDetails() {
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

    public SimpleIntegerProperty amountProperty() {
        return amount;
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

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getAmount() {
        return amount.get();
    }
}
