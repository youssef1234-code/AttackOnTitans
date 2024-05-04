package game.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class mainController {
    

    @FXML
    private Label Title;

    @FXML
    private Button startGame,credits,quitGame;

    @FXML
    private AnchorPane scenePane;

    public Stage stage;

    public void exitGame(ActionEvent event){
        stage = (Stage) scenePane.getScene().getWindow();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're about to Quit!!");
        alert.setContentText("You will lose all your progress if you quit !!");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You exited the game through quit button");
            stage.close();
        }
    } 

 


}

