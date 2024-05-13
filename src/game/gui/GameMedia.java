package game.gui;


import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;



abstract public class GameMedia {
    private static MediaPlayer gameBackgroundSound = new MediaPlayer(new Media(BGMedia.class.getResource("assets/BattleMusic.mp3").toString()));

    private boolean isMute = false;

    public void muteMedia(ActionEvent event, Button muteButton){
        if (isMute) {
            gameBackgroundSound.setVolume(1.0);
            isMute = false;
            muteButton.setText("Mute"); 
        } else {
            gameBackgroundSound.setVolume(0.0);
            isMute = true;
            muteButton.setText("Unmute"); 
        }
    }

    public void muteNoText(ActionEvent event){
        gameBackgroundSound.setVolume(0);
    }

    public void playMedia(){
        gameBackgroundSound.seek(gameBackgroundSound.getStartTime());
        gameBackgroundSound.setVolume(1);
        gameBackgroundSound.setAutoPlay(true);
        gameBackgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void pauseMedia(){
        gameBackgroundSound.pause();
    }

    public void resumeMedia(){
        gameBackgroundSound.play();
    }



    
}
