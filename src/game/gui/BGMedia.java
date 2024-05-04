package game.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

abstract public class BGMedia {
    private static MediaPlayer bgAudioPlayer = new MediaPlayer(new Media(Media.class.getResource("assets/bgmusic.mp3").toExternalForm()));
    private static MediaPlayer mediaPlayer = new MediaPlayer(new Media(Media.class.getClass().getResource("assets/bgvideo.mp4").toExternalForm()));

    
    
}
