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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Parent;
import javafx.scene.Node;

public class CreditsController implements Initializable{ 
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

    private MediaPlayer bgAudioPlayer = new MediaPlayer(new Media(getClass().getResource("assets/bgmusic.mp3").toExternalForm()));
    public boolean isMute = false;


    public void mute(ActionEvent event){
        if (isMute) {
            bgAudioPlayer.setVolume(1.0);
            isMute = false;
            muteButton.setText("Mute"); 
        } else {
            bgAudioPlayer.setVolume(0.0);
            isMute = true;
            muteButton.setText("Unmute"); 
        }
    }

    public void goToMainMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("MainMenuScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        bgAudioPlayer.stop();

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
        String videoFile = getClass().getResource("assets/bgvideo.mp4").toExternalForm();
        Media media = new Media(videoFile);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundVideo.setMediaPlayer(mediaPlayer);


        bgAudioPlayer.setAutoPlay(true);
        bgAudioPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }


    

    

    
}
