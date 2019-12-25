package Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Fine implements Initializable {
    @FXML private TextField dueYear;
    @FXML private TextField dueMonth;
    @FXML private TextField dueDay;
    @FXML private TextField returnYear;
    @FXML private TextField returnMonth;
    @FXML private TextField returnDay;
    @FXML private TextField chargeDay;
    @FXML private TextField maxCharge;
    @FXML private Button closeButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void calculate(ActionEvent event) {
        if(dueYear.getText()==null||dueMonth.getText()==null||dueDay.getText()==null||
                returnYear.getText()==null||returnMonth.getText()==null||returnDay.getText()==null||
                chargeDay.getText()==null||maxCharge.getText()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Overdue Fines");
            alert.setContentText("Please fill all fields with numbers");
            alert.showAndWait();
        }
        int dY = Integer.parseInt(dueYear.getText());
        int dM = Integer.parseInt(dueMonth.getText());
        int dD = Integer.parseInt(dueDay.getText());
        int rY = Integer.parseInt(returnYear.getText());
        int rM = Integer.parseInt(returnMonth.getText());
        int rD = Integer.parseInt(returnDay.getText());
        int chargePerDay = Integer.parseInt(chargeDay.getText());
        int max = Integer.parseInt(maxCharge.getText());
        GregorianCalendar dueDate = new GregorianCalendar(dY,dM,dD);
        GregorianCalendar returnDate = new GregorianCalendar(rY,rM,rD);
        LibraryBook libraryBook = new LibraryBook(dueDate,chargePerDay,max);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Overdue Fines");
        alert.setContentText("Your overdue fine is " + libraryBook.computeCharge(returnDate));
        alert.showAndWait();
        return;
    }
    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
