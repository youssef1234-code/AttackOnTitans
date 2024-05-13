package game.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseMenuController {
    @FXML
    private AnchorPane pauseMenuPane;

    @FXML
    private Button quitGame;

    @FXML
    private Button muteButton;

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

    public void exitGame(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're about to Quit!!");
        alert.setContentText("You will lose all your progress if you quit !!");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        dialogPane.getStyleClass().add("exitDialog");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You exited the game through quit button");
            stage.close();
        }
    }

    public void pauseMenuMute(ActionEvent event){

    }

    public void escape(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = scene.getRoot();
        AnchorPane pane = (AnchorPane) root.lookup("#MainParent");
        pane.getChildren().remove(pauseMenuPane);

    }
    
}
