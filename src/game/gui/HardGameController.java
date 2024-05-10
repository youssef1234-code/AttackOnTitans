package game.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HardGameController implements Initializable{

    //Lane Anchor Panes
    @FXML
    private AnchorPane Lane1Pane;

    @FXML
    private AnchorPane Lane2Pane;
    
    @FXML
    private AnchorPane Lane3Pane;
    
    @FXML
    private AnchorPane Lane4Pane;
    
    @FXML
    private AnchorPane Lane5Pane;

    // Wall Image Views
    @FXML
    private ImageView Wall1;

    @FXML
    private ImageView Wall2;

    @FXML
    private ImageView Wall31;

    @FXML
    private ImageView Wall32;

    @FXML
    private ImageView Wall4;

    @FXML
    private ImageView Wall51;

    @FXML
    private ImageView Wall52;

    //Weapon Shop Buttons
    @FXML
    private Button purchaseSniperButton;

    @FXML
    private Button purchasePiercingButton;

    @FXML
    private Button purchaseVolleyButton;

    @FXML
    private Button purchaseTrapButton;
    
    //Menu and pass buttons
    @FXML
    private Button passTurnButton;

    @FXML
    private Button SettingsButton;
  
    //Labels
    @FXML
    private Label ScoreText;

    @FXML
    private Label phaseAndTurnText;

    @FXML
    private Label resourcesGatheredText;




   
    public void initialize(URL location, ResourceBundle resources) {
        MediaPlayer backgroundMusic = new MediaPlayer(new Media(HardGameController.class.getResource("assets/BattleMusic.mp3").toString()));
        backgroundMusic.setAutoPlay(true);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
    }

   
   
    



}
