package game.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import game.engine.lanes.Lane;
import game.engine.titans.PureTitan;
import game.gui.titansGUI.PureTitanGUI;
import game.engine.Battle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private Label scoreText;

    @FXML
    private Label phaseAndTurnText;

    @FXML
    private Label resourcesGatheredText;

    @FXML
    private Label lane1Danger;
    
    @FXML
    private Label lane2Danger;
        
    @FXML
    private Label lane3Danger;
    
    @FXML
    private Label lane4Danger;
        
    @FXML
    private Label lane5Danger;

    //Prgress Bars
    @FXML
    private ProgressBar wall1Bar;

    @FXML
    private ProgressBar wall2Bar;
    
    @FXML
    private ProgressBar wall3Bar;
    
    @FXML
    private ProgressBar wall4Bar;
    
    @FXML
    private ProgressBar wall5Bar;


    
    public static Battle battle;


    public PureTitanGUI testTitan = new PureTitanGUI(new PureTitan(1000, 0100, 100, 1100, 05, 20, 1));




   
    public void initialize(URL location, ResourceBundle resources){
        try {
            String resourcePath = "assets/BattleMusic.mp3";
            URL resourceUrl = getClass().getResource(resourcePath);
             battle = new Battle(1, 0, 1090, 5, 125);
            if (resourceUrl == null) {
                System.err.println("Failed to load resource: " + resourcePath);
            } else {
                System.out.println("Resource URL: " + resourceUrl);
                MediaPlayer backgroundMusic = new MediaPlayer(new Media(resourceUrl.toString()));
                backgroundMusic.setAutoPlay(true);
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            }
        } catch (Exception e) {
            System.err.println("Error initializing media player: " + e.getMessage());
        }
    

        Lane1Pane.getChildren().add(testTitan.pureTitanView);
        AnchorPane.setRightAnchor(testTitan.pureTitanView, 0.0);
        
        
        
    }   

    public void updateTexts(){
        resourcesGatheredText.setText("" +battle.getResourcesGathered());
        phaseAndTurnText.setText("" + battle.getBattlePhase() + " (" + battle.getNumberOfTurns() + ")" );
        scoreText.setText("" + battle.getScore());
        
        ArrayList<Lane> lanes = battle.getOriginalLanes();
        for(int i = 0; i < lanes.size(); i++){
            if(!lanes.get(i).isLaneLost()){
                switch(i){
                    case 0: lane1Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 1: lane2Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 2: lane3Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 3: lane4Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 4: lane5Danger.setText(""+lanes.get(i).getDangerLevel());break;
                }

                switch(i){
                    case 0: wall1Bar.setProgress(lanes.get(i).getLaneWall().getCurrentHealth()/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 1: wall2Bar.setProgress(lanes.get(i).getLaneWall().getCurrentHealth()/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 2: wall3Bar.setProgress(lanes.get(i).getLaneWall().getCurrentHealth()/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 3: wall4Bar.setProgress(lanes.get(i).getLaneWall().getCurrentHealth()/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 4: wall5Bar.setProgress(lanes.get(i).getLaneWall().getCurrentHealth()/lanes.get(i).getLaneWall().getBaseHealth());break;
                }
            }

        }

        
        testTitan.translate();

        System.out.println("A7a");
    }

    

   
   
    



}
