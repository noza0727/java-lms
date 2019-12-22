package Library;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.*;

import java.sql.Connection;


public class Main extends Application {
public static String changeWindow = "Authorization.fxml";
        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setOnCloseRequest(e-> Platform.exit());
            primaryStage.setResizable(false);
            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);

        }
}
