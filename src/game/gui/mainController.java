package game.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class mainController implements Initializable {
    

    @FXML
    private Label Title;

    @FXML
    private Button startGame,credits,quitGame,muteButton;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private MediaView backgroundVideo;

    private MediaPlayer bgAudioPlayer = new MediaPlayer(new Media(getClass().getResource("assets/bgmusic.mp3").toExternalForm()));
    public boolean isMute = false;

    private Scene scene;
    private Parent root;
    public Stage stage;
    private final String css = getClass().getResource("application.css").toExternalForm();

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

        public void goToCredits(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CreditsScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        bgAudioPlayer.stop();

        stage.setScene(scene);
        stage.setScene(scene);
        scene.setOnKeyPressed(ev ->{
            if(ev.getCode() == KeyCode.F11)
                stage.setFullScreen(!stage.isFullScreen());
        });
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String videoFile = getClass().getResource("assets/bgvideo.mp4").toExternalForm();
        Media media = new Media(videoFile);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundVideo.setMediaPlayer(mediaPlayer);

        bgAudioPlayer.setAutoPlay(true);
        bgAudioPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        
        
        startGame.setOnKeyPressed(this::handleButtonKeyPress);
        credits.setOnKeyPressed(this::handleButtonKeyPress);
        quitGame.setOnKeyPressed(this::handleButtonKeyPress);
    } 

    private void handleButtonKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Button sourceButton = (Button) event.getSource();
            sourceButton.fire();
        }
    }

    


}

