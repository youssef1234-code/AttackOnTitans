package game.gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaView;
import javafx.scene.Parent;
import javafx.scene.Node;

public class CreditsController extends BGMedia implements Initializable{ 
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final String css = getClass().getResource("application.css").toExternalForm();

    @FXML
    private Label textBox1, textBox2;

    @FXML
    private Button backButton, muteButton;

    @FXML
    private MediaView backgroundVideo;

    public void mute(ActionEvent event){
      muteMedia(event, muteButton);
    }

    public void goToMainMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        Button nextMuteButton = (Button) root.lookup("#muteButton");
        if(muteButton.getText().equals("Mute"))
            nextMuteButton.setText("Mute");
        else 
            nextMuteButton.setText("Unmute");


        stage.setScene(scene);
        scene.setOnKeyPressed(ev ->{
            if(ev.getCode() == KeyCode.F11)
                stage.setFullScreen(!stage.isFullScreen());
        });
        stage.setResizable(false);
        stage.setFullScreen(true);

        stage.show();

    }

    public void initialize(URL location, ResourceBundle resources){
       resumeMedia(backgroundVideo);
    }

}
