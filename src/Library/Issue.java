package Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Issue implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField isbn;

    public void inflateIssue(BookDetails books){
        isbn.setText(books.getIsbn());
        isbn.setEditable(false);
    }

    void setupIssueTable() {
        String TABLE_NAME = "ISSUE";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();

            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "     bookID varchar(200) primary key,\n"
                        + "	memberID varchar(200),\n"
                        + "	issueTime timestamp default CURRENT_TIMESTAMP,\n"
                        + "	renew_count integer default 0,\n"
                        + "	FOREIGN KEY (bookID) REFERENCES BOOK(id),\n"
                        + "	FOREIGN KEY (memberID) REFERENCES MEMBER(id)"
                        + " )");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " --- setupDatabase");
        }
    }

    public void submitAction(ActionEvent event) {
        String studentId = id.getText();
        String bookIsbn = isbn.getText();
        String srt = "INSERT INTO ISSUE (studentId,bookIsbn) VALUES ("
                + "'" + studentId + "'," + "'" + bookIsbn + "')";
        String str2 = "UPDATE BOOK SET isAvailable = no WHERE id = '" + bookIsbn + "'";
        System.out.println(srt);
        System.out.println(str2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("The book is successfully issued");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

