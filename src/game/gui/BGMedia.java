package game.gui;


import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;



abstract public class BGMedia {
    private static MediaPlayer bgAudioPlayer = new MediaPlayer(new Media(BGMedia.class.getResource("assets/bgmusic.mp3").toString()));
    private static MediaPlayer videoPlayer = new MediaPlayer(new Media(BGMedia.class.getResource("assets/bgvideo.mp4").toString()));

    private boolean isMute = false;

    public void muteMedia(ActionEvent event, Button muteButton){
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

    public void muteNoText(ActionEvent event){
        bgAudioPlayer.setVolume(0);
    }

    public void playMedia(MediaView backgroundVideo){
        videoPlayer.setAutoPlay(true);
        videoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundVideo.setMediaPlayer(videoPlayer);

        bgAudioPlayer.setAutoPlay(true);
        bgAudioPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void pauseMedia(){
        videoPlayer.pause();
        bgAudioPlayer.pause();
    }

    public void resumeMedia(MediaView backgroundVideo){
        backgroundVideo.setMediaPlayer(videoPlayer);
        videoPlayer.play();
        bgAudioPlayer.play();
    }



    
}
