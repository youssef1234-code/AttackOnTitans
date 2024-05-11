package game.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.io.InputStream;

import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.Titan;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.gui.titansGUI.PureTitanGUI;
import game.gui.titansGUI.TitanGUI;
import game.gui.weaponsGUI.PiercingCannonGUI;
import game.gui.weaponsGUI.SniperCannonGUI;
import game.gui.weaponsGUI.VolleySpreadCannonGUI;
import game.gui.weaponsGUI.WallTrapGUI;
import game.gui.weaponsGUI.WeaponsGUI;
import game.gui.titansGUI.AbnormalTitanGUI;
import game.gui.titansGUI.ArmoredTitanGUI;
import game.gui.titansGUI.ColossalTitanGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HardGameController implements Initializable{

    @FXML
    private AnchorPane MainParent;
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


    
    private static Battle battle;
    private AnchorPane [] lanesGui = new AnchorPane[5];
    private ArrayList<ArrayList<TitanGUI>> titanImages = new ArrayList<ArrayList<TitanGUI>>();






   
    public void initialize(URL location, ResourceBundle resources){
        try {
            String resourcePath = "assets/BattleMusic.mp3";
            URL resourceUrl = getClass().getResource(resourcePath);
            battle = new Battle(1, 0, 50, 5, 125);
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
        updateTexts();

        setupDrag(purchaseSniperButton, "SniperCannon");
        setupDrag(purchasePiercingButton, "PiercingSpreadCannon");
        setupDrag(purchaseVolleyButton, "VolleyCannon");
        setupDrag(purchaseTrapButton, "wallTrap");
        
        lanesGui[0] = Lane1Pane;
        lanesGui[1] = Lane2Pane;
        lanesGui[2] = Lane3Pane;
        lanesGui[3] = Lane4Pane;
        lanesGui[4] = Lane5Pane;

        titanImages.add(new ArrayList<TitanGUI>());
        titanImages.add(new ArrayList<TitanGUI>());
        titanImages.add(new ArrayList<TitanGUI>());
        titanImages.add(new ArrayList<TitanGUI>());
        titanImages.add(new ArrayList<TitanGUI>());
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
                    case 0: wall1Bar.setProgress((lanes.get(i).getLaneWall().getCurrentHealth() + 0.0)/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 1: wall2Bar.setProgress((lanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 2: wall3Bar.setProgress((lanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 3: wall4Bar.setProgress((lanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/lanes.get(i).getLaneWall().getBaseHealth());break;
                    case 4: wall5Bar.setProgress((lanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/lanes.get(i).getLaneWall().getBaseHealth());break;
                }
            }
        }
            
    }

    public void addTitansToLane(){
        for(int j = 0; j < lanesGui.length; j++ ){
        PriorityQueue<Titan> laneTitans =  battle.getOriginalLanes().get(j).getTitans();
        PriorityQueue<Titan> temp = new PriorityQueue<Titan>();
        ArrayList<Titan> titanArray = new ArrayList<Titan>();
        while(!laneTitans.isEmpty()){
            titanArray.add(laneTitans.peek());
            temp.add(laneTitans.poll());
        }
        while(!temp.isEmpty()){
            laneTitans.add(temp.poll());
        }

        
        if(!battle.getOriginalLanes().get(j).isLaneLost())
            for(int i = 0; i < titanArray.size(); i++){
                if(titanArray.get(i).getDistance()==50){
                    Random random = new Random();
                    int offset = random.nextInt(10);
                    offset = (random.nextBoolean())? offset*-1: offset;

                    switch (titanArray.get(i).getDangerLevel()) {
                        
                        case 1:
                            PureTitanGUI  pureTitan = new PureTitanGUI((PureTitan) titanArray.get(i));
                            lanesGui[j].getChildren().add(pureTitan.pureTitanView);
                            titanImages.get(j).add(pureTitan);
                            AnchorPane.setRightAnchor(pureTitan.pureTitanView, 0.0);
                            AnchorPane.setTopAnchor(pureTitan.pureTitanView, offset + 0.0);
                            break;
                        case 2:
                            AbnormalTitanGUI  abnormalTitan = new AbnormalTitanGUI((AbnormalTitan) titanArray.get(i));
                            lanesGui[j].getChildren().add(abnormalTitan.abnormalTitanView);
                            titanImages.get(j).add(abnormalTitan);
                            AnchorPane.setRightAnchor(abnormalTitan.abnormalTitanView, 0.0);
                            AnchorPane.setTopAnchor(abnormalTitan.abnormalTitanView, offset + 0.0);
                            break;
                        case 3:
                            ArmoredTitanGUI  armoredTitan = new ArmoredTitanGUI((ArmoredTitan) titanArray.get(i));
                            lanesGui[j].getChildren().add(armoredTitan.armoredTitanView);
                            titanImages.get(j).add(armoredTitan);
                            AnchorPane.setRightAnchor(armoredTitan.armoredTitanView, 0.0);
                            AnchorPane.setTopAnchor(armoredTitan.armoredTitanView, offset + 0.0);
                            break;
                        case 4:
                            ColossalTitanGUI  colossalTitan = new ColossalTitanGUI((ColossalTitan) titanArray.get(i));
                            lanesGui[j].getChildren().add(colossalTitan.colossalTitanView);
                            titanImages.get(j).add(colossalTitan);
                            AnchorPane.setRightAnchor(colossalTitan.colossalTitanView, 0.0);
                            AnchorPane.setTopAnchor(colossalTitan.colossalTitanView, offset + 0.0);
                            break;
                    }

                }
            }
        }
    }

    public void moveTitans(){
        for(int i = 0; i < lanesGui.length; i++){
            for(int j = 1; j < lanesGui[i].getChildren().size(); j++)
                lanesGui[i].getChildren().get(j);
        }
    }

    private void setupDrag(Button button, String weaponName) {
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(weaponName); 
            db.setContent(content);
            event.consume();
        });
    }

    public void onDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getSource() && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    public void onDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        String weaponName = db.getString();
        WeaponsGUI chosenWeapon = null;
        AnchorPane targetPane = (AnchorPane) event.getSource();
        if (db.hasString()) {
            switch(weaponName){
                case "SniperCannon": chosenWeapon = new SniperCannonGUI(new SniperCannon(10));break;
                case "PiercingSpreadCannon": chosenWeapon = new PiercingCannonGUI(new PiercingCannon(35));break;
                case "VolleyCannon": chosenWeapon = new VolleySpreadCannonGUI(new VolleySpreadCannon(5, 20, 50));break;
                case "wallTrap":chosenWeapon = new WallTrapGUI(new WallTrap(100));break;
            }

            int weaponCode = chosenWeapon.getweaponCode();
            Lane lane  = null;
            int chosenLane = 0;
            if(targetPane == Lane1Pane){
                lane = battle.getOriginalLanes().get(0);
                chosenLane = 1;
            }
            else if(targetPane == Lane2Pane){
                lane = battle.getOriginalLanes().get(1);
                chosenLane = 2;
            }

            else if(targetPane == Lane3Pane){
                lane = battle.getOriginalLanes().get(2);
                chosenLane = 3;
            }

            else if(targetPane == Lane4Pane){
                lane = battle.getOriginalLanes().get(3);
                chosenLane = 4;
            }

            else if(targetPane == Lane5Pane){
                lane = battle.getOriginalLanes().get(4);
                chosenLane = 5;
            }

            try{
                this.battle.purchaseWeapon(weaponCode ,lane);
                try{
                    targetPane.getChildren().add(chosenWeapon.getPane());
                    success = true;
                }
                catch(NullPointerException Exception){
                    System.out.println("error in choosing a weapon !!");
                }
            }
            catch(InsufficientResourcesException Exception ){
                //TODO handel InsufficientResourcesException (Show a red label or something !!!)
                System.out.println("NOT ENOUGH RESOURCES!! ");
            }
            catch(InvalidLaneException Exception ){
                //Lane1Pane
                switch(chosenLane){
                    case 1: Wall1.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 2: Wall2.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 3: Wall31.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 4: Wall4.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 5: Wall51.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                }

                if(chosenLane == 3)
                    Wall32.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
                if(chosenLane == 5)
                    Wall52.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString())); 

                switch(chosenLane){
                    case 1: MainParent.getChildren().remove(Lane1Pane);break;
                    case 2: MainParent.getChildren().remove(Lane2Pane);break;
                    case 3: MainParent.getChildren().remove(Lane3Pane);break;
                    case 4: MainParent.getChildren().remove(Lane4Pane);break;
                    case 5: MainParent.getChildren().remove(Lane5Pane);break;
                } 
            }
        }
        event.setDropCompleted(success);
        event.consume();
        addTitansToLane();
        updateTexts();
    }

    public void skipTurn(ActionEvent event){
        ArrayList<Lane> currentLanes = battle.getOriginalLanes();
        for(int i=0;i<currentLanes.size();i++){
            if(currentLanes.get(i).isLaneLost()){
                switch(i+1){
                    case 1: MainParent.getChildren().remove(Lane1Pane);break;
                    case 2: MainParent.getChildren().remove(Lane2Pane);break;
                    case 3: MainParent.getChildren().remove(Lane3Pane);break;
                    case 4: MainParent.getChildren().remove(Lane4Pane);break;
                    case 5: MainParent.getChildren().remove(Lane5Pane);break;
                }
                switch(i+1){
                    case 1: Wall1.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 2: Wall2.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 3: Wall31.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 4: Wall4.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                    case 5: Wall51.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                }
                if(i+1 == 3)
                    Wall32.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
                if(i+1 == 5)
                    Wall52.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString())); 

            }
        }
        battle.passTurn();
        addTitansToLane();
        updateTexts();
    }


}
