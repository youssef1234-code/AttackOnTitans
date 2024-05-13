package game.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.io.IOException;

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
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.util.Duration;

public class HardGameController implements Initializable{

    @FXML
    private AnchorPane MainParent;
    //Lane Anchor Panes
    @FXML
    private AnchorPane Lane1Pane;
    private static double availableposXLane1 = 500.0;
    private boolean Lane1hasTrap = false;

    @FXML
    private AnchorPane Lane2Pane;
    private static double availableposXLane2 = 500.0;
    private boolean Lane2hasTrap = false;

    @FXML
    private AnchorPane Lane3Pane;
    private static double availableposXLane3 = 500.0;
    private boolean Lane3hasTrap = false;
    
    @FXML
    private AnchorPane Lane4Pane;
    private static  double availableposXLane4 = 500.0;
    private boolean Lane4hasTrap = false;

    @FXML
    private AnchorPane Lane5Pane;
    private static  double availableposXLane5 = 500.0;
    private boolean Lane5hasTrap = false;

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

    //Game Over Screen
    @FXML
    private Button gameOverBackButton;

    @FXML
    private Label finalScoreLabel;

    @FXML
    private ImageView errorDialogue;

    @FXML
    private ImageView invalidLaneDialogue;

    


    
    private static Battle battle;
    private AnchorPane [] lanesGui = new AnchorPane[5];
    private ArrayList<ArrayList<TitanGUI>> titanImages = new ArrayList<ArrayList<TitanGUI>>();
    private Thread gameOverThread;
    private Parent root;






   
    public void initialize(URL location, ResourceBundle resources){
        try {
            String resourcePath = "assets/BattleMusic.mp3";
            URL resourceUrl = getClass().getResource(resourcePath);
            battle = new Battle(1, 0, 50, 5, 125);
            root = FXMLLoader.load(getClass().getResource("FXML/GameOverScene.fxml"));
            root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            if (resourceUrl == null) {
                System.err.println("Failed to load resource: " + resourcePath);
            } else {
                MediaPlayer backgroundMusic = new MediaPlayer(new Media(resourceUrl.toString()));
                backgroundMusic.setAutoPlay(true);
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.setVolume(1);
            }
        } catch (Exception e) {
            System.err.println("Error initializing media player: " + e.getMessage());
        }
        updateTexts();
        /*gameOverThread = new Thread(new Runnable(){

            @Override
            public void run() {
               while (true) {
                System.out.println(100);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(battle.isGameOver()){
                    try{
                        isGameOverDisplay();
                        break;
                    }catch(IOException e){
         
                    }
                 }
               }
            }


        });
        gameOverThread.start();*/
        
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
            Lane currentLane  = lanes.get(i);
            if(!currentLane.isLaneLost()){
                switch(i){
                    case 0: lane1Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 1: lane2Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 2: lane3Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 3: lane4Danger.setText(""+lanes.get(i).getDangerLevel());break;
                    case 4: lane5Danger.setText(""+lanes.get(i).getDangerLevel());break;
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
                    int offset = random.nextInt(40);
                    //offset = (random.nextBoolean())? offset*-1: offset;

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
        for(int i = 0; i < titanImages.size(); i++){
            for(int j = 0; j < titanImages.get(i).size(); j++)
                    if(titanImages.get(i) != null && titanImages.get(i).get(j) != null)
                        titanImages.get(i).get(j).translate();
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
        System.out.println("Drag is Left");
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
                battle.purchaseWeapon(weaponCode ,lane);
                System.out.println("Weapon is being purchased!!");
                    if(weaponCode == 4){
                        targetPane.getChildren().add(chosenWeapon.getPane());
                        targetPane.setLeftAnchor(chosenWeapon.getPane(), 650.0);
                        targetPane.setTopAnchor(chosenWeapon.getPane(), 20.0);
                        if(chosenLane == 1){
                            if(Lane1hasTrap)
                                targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
                            Lane1hasTrap = !Lane1hasTrap;
                        }
                        else if(chosenLane == 2){
                            if(Lane2hasTrap)
                                targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
                            Lane2hasTrap = !Lane2hasTrap;
                        } else if(chosenLane == 3){
                            if(Lane3hasTrap)
                                targetPane.setTopAnchor(chosenWeapon.getPane(),70.0);
                            Lane3hasTrap = !Lane3hasTrap;
                        } else if(chosenLane == 4){
                            if(Lane4hasTrap)
                                targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
                            Lane4hasTrap = !Lane4hasTrap;
                        } else if(chosenLane == 5){
                            if(Lane5hasTrap)
                                targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
                            Lane5hasTrap = !Lane5hasTrap;
                        }

                    }else{
                        double distanceInPixels = 0;
                        switch(chosenLane){
                            case 1 :distanceInPixels = availableposXLane1;break;
                            case 2 :distanceInPixels = availableposXLane2;break;
                            case 3 :distanceInPixels = availableposXLane3;break;
                            case 4 :distanceInPixels = availableposXLane4;break;
                            case 5 :distanceInPixels = availableposXLane5;break;
                        }
                        switch(chosenLane){
                            case 1 :availableposXLane1-=125;break;
                            case 2 :availableposXLane2-=125 ;break;
                            case 3 :availableposXLane3-=125 ;break;
                            case 4 :availableposXLane4-=125 ;break;
                            case 5 :availableposXLane5-=125;break;
                        }
                        if(distanceInPixels>=0){
                            targetPane.getChildren().addAll(chosenWeapon.getPane(),chosenWeapon.getBallPane());
                            targetPane.setLeftAnchor(chosenWeapon.getPane(), distanceInPixels);
                        }
                        if(weaponCode == 3 || weaponCode == 2 )
                            targetPane.setTopAnchor(chosenWeapon.getPane(), 20.0);
        
                    }
                    success = true;
                //chosenWeapon.attackTitans();
                
            }
            catch(InsufficientResourcesException Exception ){
                ///errorDialogue.setOpacity(1.0);
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errorDialogue);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), errorDialogue);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            fadeIn.play();
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            fadeOut.play();
                        })
                );
                timeline.play();               
            }
            catch(InvalidLaneException Exception ){
                //Lane1Pane Dialogue box
                System.out.println("Lane is NULL??? " + lane == null);
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), invalidLaneDialogue);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), invalidLaneDialogue);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            fadeIn.play();
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            fadeOut.play();
                        })
                );
                timeline.play();       
            
            }
        }
        System.out.println("Excuted");
        event.setDropCompleted(success);
        event.consume();

        moveTitans();
                weaponsAttackTitans();
                titansAttack();
                addTitansToLane();
                updateTexts();
                if(battle.isGameOver()){
                    try{
                        isGameOverDisplay();
                        
                    }catch(IOException e){
         
                    }
                }
    }
    public void weaponsAttackTitans(){
        //Iterates over the all the weapons Guis and performs the attack of weapons on the titans only in the GUI 
        //Balls should move
        //Titan health bars should be edited 
        //Titan death also be triggered

    }

    public void titansAttack(){
        ArrayList<Lane> currentLanes = battle.getOriginalLanes();
        for(int i=0;i<currentLanes.size();i++){
            for(int j = 0; j < titanImages.get(i).size(); j++) 
                if(!titanImages.get(i).isEmpty() && titanImages.get(i).get(j) != null)
                    titanImages.get(i).get(j).attack();
            
            
            System.out.println((i+1) + " " + currentLanes.get(i).getLaneWall().getCurrentHealth());
            switch(i){
                case 0: wall1Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/currentLanes.get(i).getLaneWall().getBaseHealth());break;
                case 1: wall2Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/currentLanes.get(i).getLaneWall().getBaseHealth());break;
                case 2: wall3Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/currentLanes.get(i).getLaneWall().getBaseHealth());break;
                case 3: wall4Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/currentLanes.get(i).getLaneWall().getBaseHealth());break;
                case 4: wall5Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth()+ 0.0)/currentLanes.get(i).getLaneWall().getBaseHealth());break;
            }

            
            if(currentLanes.get(i).isLaneLost()){

            switch(i+1){
                case 1: Wall1.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                case 2: Wall2.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                case 3: Wall31.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                case 4: Wall4.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
                case 5: Wall51.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));break;
            }

            if(i+1 == 3)
                Wall32.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
            if(i+1 == 5){
                Wall52.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString())); 
                Wall52.setFitWidth(94.0);
                Wall52.setFitHeight(199.0);
            }
               
            switch(i+1){
                case 1: Lane1Pane.getChildren().removeAll(Lane1Pane.getChildren());break;
                case 2: Lane2Pane.getChildren().removeAll(Lane2Pane.getChildren());break;
                case 3: Lane3Pane.getChildren().removeAll(Lane3Pane.getChildren());break;
                case 4: Lane4Pane.getChildren().removeAll(Lane4Pane.getChildren());break;
                case 5: Lane5Pane.getChildren().removeAll(Lane5Pane.getChildren());break;
            } 

            }

        }
    }

    public void skipTurn(ActionEvent event){
        try {
            battle.passTurn();
        } catch (Exception e) {
            // TODO: handle exception
        }
        moveTitans();
        weaponsAttackTitans();
        titansAttack();
        addTitansToLane();
        updateTexts();
        
        if(battle.isGameOver()){
            try{
                isGameOverDisplay();
                
            }catch(IOException e){
 
            }
        }
        
       
    }

    public void isGameOverDisplay() throws IOException{
        System.out.println("Works");
  
        ((Label)root.getChildrenUnmodifiable().get(1)).setText("" + battle.getScore());
        MainParent.getChildren().add(root);
        AnchorPane.setTopAnchor(root,240.0);
        AnchorPane.setLeftAnchor(root,460.0);

        passTurnButton.setDisable(true);
        SettingsButton.setDisable(true);
        purchasePiercingButton.setDisable(true);
        purchaseSniperButton.setDisable(true);
        purchaseTrapButton.setDisable(true);
        purchaseVolleyButton.setDisable(true);
        
    }

    


}
