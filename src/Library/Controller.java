package Library;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.*;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class Controller {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Button btn_signIn;

    @FXML
    private Button btn_registration;

    @FXML
    public void handleCloseButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void signInWindow(){
        btn_signIn.setOnAction(event->{
            btn_signIn.getScene().getWindow().hide();
        });

        FXMLLoader loader = new FXMLLoader();
        //-- window which should appear depending on what kind of user is signing in
        loader.setLocation(getClass().getResource("/Library/ the location of the appearing window"));

        try{
            loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void registerControl(){
        btn_registration.setOnAction(event -> {
            btn_registration.getScene().getWindow().hide();
        });
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Library/StudentRegister.fxml"));

        
    }


}
