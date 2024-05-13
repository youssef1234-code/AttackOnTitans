package game.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameOverController {
    @FXML
    private Button gameOverBackButton;
    
    
    public void goToMainMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/MainMenuScene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


        stage.setScene(scene);
        scene.setOnKeyPressed(ev ->{
            if(ev.getCode() == KeyCode.F11)
                stage.setFullScreen(!stage.isFullScreen());
        });
        stage.setResizable(false);
        stage.setFullScreen(true);

        stage.show();

    }
    
}
