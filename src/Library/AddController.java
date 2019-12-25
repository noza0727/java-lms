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
    private TextField amount;

    private Boolean isModify = Boolean.FALSE;

    @Override public void initialize(URL url, ResourceBundle rb){ }

    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        isbn.setText(null);
        title.setText(null);
        subject.setText(null);
        author.setText(null);
        year.setText(null);
        amount.setText(null);
    }

    public void save(MouseEvent mouseEvent) {
        String bookISBN = isbn.getText();
        String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookSubject = subject.getText();
        int bookYear = Integer.parseInt(year.getText());
        int bookAmount = Integer.parseInt(amount.getText());


        if(bookISBN.isEmpty() || bookAmount==0 || bookTitle.isEmpty() ||
                bookSubject.isEmpty() || bookAuthor.isEmpty() || bookYear==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled");
            alert.showAndWait();
            return;
        }

        String insertion = "INSERT INTO books VALUES (" +
                "'" + bookISBN + "'," +
                "'" + bookTitle + "'," +
                "'" + bookAuthor + "'," +
                "'" + bookSubject + "'," +
                "'" + bookYear + "'," +
                "'" + bookAmount+"')";
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



}
