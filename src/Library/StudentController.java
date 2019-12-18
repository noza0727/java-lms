package Library;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
