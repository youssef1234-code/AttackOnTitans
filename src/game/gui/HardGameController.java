package game.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class HardGameController extends GameMedia implements Initializable {

  @FXML
  private AnchorPane MainParent;
  //Lane Anchor Panes
  @FXML
  private AnchorPane Lane1Pane;
  private static double availableposXLane1 = 0;
  private boolean Lane1hasTrap = false;
  private List < WeaponsGUI > Lane1Weapons = new ArrayList < WeaponsGUI > ();
  private List < Boolean > Lane1WeaponsStatus = new ArrayList< Boolean > ();

  @FXML
  private AnchorPane Lane2Pane;
  private static double availableposXLane2 = 0;
  private boolean Lane2hasTrap = false;
  private List < WeaponsGUI > Lane2Weapons = new ArrayList < WeaponsGUI > ();
  private List < Boolean > Lane2WeaponsStatus = new ArrayList< Boolean > ();


  @FXML
  private AnchorPane Lane3Pane;
  private static double availableposXLane3 = 0;
  private boolean Lane3hasTrap = false;
  private List < WeaponsGUI > Lane3Weapons = new ArrayList < WeaponsGUI > ();
  private List < Boolean > Lane3WeaponsStatus = new ArrayList< Boolean > ();


  @FXML
  private AnchorPane Lane4Pane;
  private static double availableposXLane4 = 0;
  private boolean Lane4hasTrap = false;
  private List < WeaponsGUI > Lane4Weapons = new ArrayList < WeaponsGUI > ();
  private List < Boolean > Lane4WeaponsStatus = new ArrayList< Boolean > ();


  @FXML
  private AnchorPane Lane5Pane;
  private static double availableposXLane5 = 0;
  private boolean Lane5hasTrap = false;
  private List < WeaponsGUI > Lane5Weapons = new ArrayList < WeaponsGUI > ();
  private List < Boolean > Lane5WeaponsStatus = new ArrayList< Boolean > ();

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
  private AnchorPane[] lanesGui = new AnchorPane[5];
  private ArrayList < ArrayList < TitanGUI >> titanImages = new ArrayList < ArrayList < TitanGUI >> ();
  private Thread gameOverThread;
  private Parent gameOverRoot;
  private Parent pauseMenuRoot;

  //Remaining Tasks:
//1- Handling Multiple Weapons 
//2- Handling Null Pointer Excpetions
//3- Updating Values accordingly
//4- Pass Turn prevent scam

  public void initialize(URL location, ResourceBundle resources) {
    try {
      availableposXLane1 = 500.0;
      availableposXLane2 = 500.0;
      availableposXLane3 = 500.0;
      availableposXLane4 = 500.0;
      availableposXLane5 = 500.0;

      battle = new Battle(1, 0, 50, 5, 125);
      gameOverRoot = FXMLLoader.load(getClass().getResource("FXML/GameOverScene.fxml"));
      gameOverRoot.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      pauseMenuRoot = FXMLLoader.load(getClass().getResource("FXML/PauseMenuScene.fxml"));
      pauseMenuRoot.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    } catch (Exception e) {
      System.err.println("Error initializing media player: " + e.getMessage());
    }
    playMedia();
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

    titanImages.add(new ArrayList < TitanGUI > ());
    titanImages.add(new ArrayList < TitanGUI > ());
    titanImages.add(new ArrayList < TitanGUI > ());
    titanImages.add(new ArrayList < TitanGUI > ());
    titanImages.add(new ArrayList < TitanGUI > ());
  }

  public void updateTexts() {
    resourcesGatheredText.setText("" + battle.getResourcesGathered());
    phaseAndTurnText.setText("" + battle.getBattlePhase() + " (" + battle.getNumberOfTurns() + ")");
    scoreText.setText("" + battle.getScore());

    ArrayList < Lane > lanes = battle.getOriginalLanes();
    for (int i = 0; i < lanes.size(); i++) {
      Lane currentLane = lanes.get(i);
      if (!currentLane.isLaneLost()) {
        switch (i) {
        case 0:
          lane1Danger.setText("" + lanes.get(i).getDangerLevel());
          break;
        case 1:
          lane2Danger.setText("" + lanes.get(i).getDangerLevel());
          break;
        case 2:
          lane3Danger.setText("" + lanes.get(i).getDangerLevel());
          break;
        case 3:
          lane4Danger.setText("" + lanes.get(i).getDangerLevel());
          break;
        case 4:
          lane5Danger.setText("" + lanes.get(i).getDangerLevel());
          break;
        }

      }

    }

  }

  public void addTitansToLane() {
    for (int j = 0; j < lanesGui.length; j++) {
      PriorityQueue < Titan > laneTitans = battle.getOriginalLanes().get(j).getTitans();
      PriorityQueue < Titan > temp = new PriorityQueue < Titan > ();
      ArrayList < Titan > titanArray = new ArrayList < Titan > ();
      while (!laneTitans.isEmpty()) {
        titanArray.add(laneTitans.peek());
        temp.add(laneTitans.poll());
      }
      while (!temp.isEmpty()) {
        laneTitans.add(temp.poll());
      }

      if (!battle.getOriginalLanes().get(j).isLaneLost())
        for (int i = 0; i < titanArray.size(); i++) {
          if (titanArray.get(i).getDistance() == 50) {
            Random random = new Random();
            int offset = random.nextInt(40);
            //offset = (random.nextBoolean())? offset*-1: offset;

            switch (titanArray.get(i).getDangerLevel()) {

            case 1:
              PureTitanGUI pureTitan = new PureTitanGUI((PureTitan) titanArray.get(i));
              lanesGui[j].getChildren().add(pureTitan.pureTitanView);
              titanImages.get(j).add(pureTitan);
              AnchorPane.setRightAnchor(pureTitan.pureTitanView, 0.0);
              AnchorPane.setTopAnchor(pureTitan.pureTitanView, offset + 0.0);
              break;
            case 2:
              AbnormalTitanGUI abnormalTitan = new AbnormalTitanGUI((AbnormalTitan) titanArray.get(i));
              lanesGui[j].getChildren().add(abnormalTitan.abnormalTitanView);
              titanImages.get(j).add(abnormalTitan);
              AnchorPane.setRightAnchor(abnormalTitan.abnormalTitanView, 0.0);
              AnchorPane.setTopAnchor(abnormalTitan.abnormalTitanView, offset + 0.0);
              break;
            case 3:
              ArmoredTitanGUI armoredTitan = new ArmoredTitanGUI((ArmoredTitan) titanArray.get(i));
              lanesGui[j].getChildren().add(armoredTitan.armoredTitanView);
              titanImages.get(j).add(armoredTitan);
              AnchorPane.setRightAnchor(armoredTitan.armoredTitanView, 0.0);
              AnchorPane.setTopAnchor(armoredTitan.armoredTitanView, offset + 0.0);
              break;
            case 4:
              ColossalTitanGUI colossalTitan = new ColossalTitanGUI((ColossalTitan) titanArray.get(i));
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

  public void moveTitans() {
    for (int i = 0; i < titanImages.size(); i++) {
      for (int j = 0; j < titanImages.get(i).size(); j++)
        if (titanImages.get(i) != null && titanImages.get(i).get(j) != null)
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
    updateTexts();
    if (event.getGestureSource() != event.getSource() && event.getDragboard().hasString()) {
      event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }
    event.consume();
  }

 // New 
 public void onDragDropped(DragEvent event) {
  System.out.println("Drag is Left");
  updateTexts();
  Dragboard db = event.getDragboard();
  boolean success = false;
  String weaponName = db.getString();
  WeaponsGUI chosenWeapon = null;
  AnchorPane targetPane = (AnchorPane) event.getSource();

  if (db.hasString()) {
      switch (weaponName) {
          case "SniperCannon":
              chosenWeapon = new SniperCannonGUI(new SniperCannon(10));
              break;
          case "PiercingSpreadCannon":
              chosenWeapon = new PiercingCannonGUI(new PiercingCannon(35));
              break;
          case "VolleyCannon":
              chosenWeapon = new VolleySpreadCannonGUI(new VolleySpreadCannon(5, 20, 50));
              break;
          case "wallTrap":
              chosenWeapon = new WallTrapGUI(new WallTrap(100));
              break;
      }

      int weaponCode = chosenWeapon.getweaponCode();
      Lane lane = null;
      int chosenLane = 0;
      if (targetPane == Lane1Pane) {
          lane = battle.getOriginalLanes().get(0);
          chosenLane = 1;
      } else if (targetPane == Lane2Pane) {
          lane = battle.getOriginalLanes().get(1);
          chosenLane = 2;
      } else if (targetPane == Lane3Pane) {
          lane = battle.getOriginalLanes().get(2);
          chosenLane = 3;
      } else if (targetPane == Lane4Pane) {
          lane = battle.getOriginalLanes().get(3);
          chosenLane = 4;
      } else if (targetPane == Lane5Pane) {
          lane = battle.getOriginalLanes().get(4);
          chosenLane = 5;
      }

      List<WeaponsGUI> laneWeapons = getLaneWeapons(chosenLane);
      WeaponsGUI existingWeapon = findWeaponInLane(laneWeapons, weaponCode);

      if (existingWeapon != null && weaponCode!=4) {
        try{
          battle.purchaseWeapon(weaponCode, lane);
          existingWeapon.increaseCount();
          //Lane1WeaponsStatus
          switch(chosenLane){
            case 1: Lane1Weapons.add(chosenWeapon) ;break;
            case 2: Lane2Weapons.add(chosenWeapon) ;break;
            case 3: Lane3Weapons.add(chosenWeapon) ;break;
            case 4: Lane4Weapons.add(chosenWeapon) ;break;
            case 5: Lane5Weapons.add(chosenWeapon) ;break;
          }
          switch(chosenLane){
            case 1: Lane1WeaponsStatus.add(false) ;break;
            case 2: Lane2WeaponsStatus.add(false) ;break;
            case 3: Lane3WeaponsStatus.add(false) ;break;
            case 4: Lane4WeaponsStatus.add(false) ;break;
            case 5: Lane5WeaponsStatus.add(false) ;break;
          }
          success = true;
          moveTitans();
          weaponsAttackTitans();
          titansAttack();
          addTitansToLane();
          updateTexts();
        }
        catch (InsufficientResourcesException e) {
          handleInsufficientResourcesException();
      } catch (InvalidLaneException e) {
          handleInvalidLaneException(lane);
      } catch (NullPointerException e) {
          handleNullPointerException();
      }    
    } else {
          try {
              battle.purchaseWeapon(weaponCode, lane);
              System.out.println("Weapon is being purchased!!");
              placeWeaponInLane(targetPane, chosenWeapon, chosenLane, weaponCode);
              switch(chosenLane){
                case 1: Lane1WeaponsStatus.add(true) ;break;
                case 2: Lane2WeaponsStatus.add(true) ;break;
                case 3: Lane3WeaponsStatus.add(true) ;break;
                case 4: Lane4WeaponsStatus.add(true) ;break;
                case 5: Lane5WeaponsStatus.add(true) ;break;
              }
              success = true;
              if (!lane.isLaneLost()) {
                  laneWeapons.add(chosenWeapon);
              }
              moveTitans();
              weaponsAttackTitans();
              titansAttack();
              addTitansToLane();
              updateTexts();
          } catch (InsufficientResourcesException e) {
              handleInsufficientResourcesException();
          } catch (InvalidLaneException e) {
              handleInvalidLaneException(lane);
          } catch (NullPointerException e) {
              handleNullPointerException();
          }
      }
  }
  System.out.println("Executed");
  event.setDropCompleted(success);
  event.consume();
  if (battle.isGameOver()) {
      handleGameOver();
  }
}

private List<WeaponsGUI> getLaneWeapons(int chosenLane) {
  switch (chosenLane) {
      case 1:
          return Lane1Weapons;
      case 2:
          return Lane2Weapons;
      case 3:
          return Lane3Weapons;
      case 4:
          return Lane4Weapons;
      case 5:
          return Lane5Weapons;
      default:
          return new ArrayList<>();
  }
}

private WeaponsGUI findWeaponInLane(List<WeaponsGUI> laneWeapons, int weaponCode) {
  for (WeaponsGUI weapon : laneWeapons) {
      if (weapon.getweaponCode() == weaponCode) {
          return weapon;
      }
  }
  return null;
}

private void placeWeaponInLane(AnchorPane targetPane, WeaponsGUI chosenWeapon, int chosenLane, int weaponCode) {
  if (weaponCode == 4) {
      targetPane.getChildren().add(chosenWeapon.getPane());
      targetPane.setLeftAnchor(chosenWeapon.getPane(), 650.0);
      targetPane.setTopAnchor(chosenWeapon.getPane(), 20.0);
      handleTrapPlacement(targetPane, chosenLane,chosenWeapon );
  } else {
      double distanceInPixels = getDistanceInPixels(chosenLane);
      updateAvailablePosition(chosenLane);
      if (distanceInPixels >= 0) {
          targetPane.getChildren().add(chosenWeapon.getPane());
          targetPane.setLeftAnchor(chosenWeapon.getPane(), distanceInPixels);
          chosenWeapon.setLeftAnchorDistanceInPixels(distanceInPixels);
      }
      if (weaponCode == 3 || weaponCode == 2)
          targetPane.setTopAnchor(chosenWeapon.getPane(), 20.0);
  }
}

private double getDistanceInPixels(int chosenLane) {
  switch (chosenLane) {
      case 1:
          return availableposXLane1;
      case 2:
          return availableposXLane2;
      case 3:
          return availableposXLane3;
      case 4:
          return availableposXLane4;
      case 5:
          return availableposXLane5;
      default:
          return 0;
  }
}

private void updateAvailablePosition(int chosenLane) {
  switch (chosenLane) {
      case 1:
          availableposXLane1 -= 125;
          break;
      case 2:
          availableposXLane2 -= 125;
          break;
      case 3:
          availableposXLane3 -= 125;
          break;
      case 4:
          availableposXLane4 -= 125;
          break;
      case 5:
          availableposXLane5 -= 125;
          break;
  }
}

private void handleTrapPlacement(AnchorPane targetPane, int chosenLane, WeaponsGUI chosenWeapon ) {
  switch (chosenLane) {
      case 1:
          if (Lane1hasTrap)
              targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
          Lane1hasTrap = !Lane1hasTrap;
          break;
      case 2:
          if (Lane2hasTrap)
              targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
          Lane2hasTrap = !Lane2hasTrap;
          break;
      case 3:
          if (Lane3hasTrap)
              targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
          Lane3hasTrap = !Lane3hasTrap;
          break;
      case 4:
          if (Lane4hasTrap)
              targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
          Lane4hasTrap = !Lane4hasTrap;
          break;
      case 5:
          if (Lane5hasTrap)
              targetPane.setTopAnchor(chosenWeapon.getPane(), 70.0);
          Lane5hasTrap = !Lane5hasTrap;
          break;
  }
}

private void handleInsufficientResourcesException() {
  FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errorDialogue);
  fadeIn.setFromValue(0);
  fadeIn.setToValue(1);
  FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), errorDialogue);
  fadeOut.setFromValue(1);
  fadeOut.setToValue(0);
  Timeline timeline = new Timeline(
      new KeyFrame(Duration.ZERO, e -> fadeIn.play()),
      new KeyFrame(Duration.seconds(2), e -> fadeOut.play())
  );
  timeline.play();
}

private void handleInvalidLaneException(Lane lane) {
  System.out.println("Lane is NULL??? " + lane == null);
  FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), invalidLaneDialogue);
  fadeIn.setFromValue(0);
  fadeIn.setToValue(1);
  FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), invalidLaneDialogue);
  fadeOut.setFromValue(1);
  fadeOut.setToValue(0);
  Timeline timeline = new Timeline(
      new KeyFrame(Duration.ZERO, e -> fadeIn.play()),
      new KeyFrame(Duration.seconds(2), e -> fadeOut.play())
  );
  timeline.play();
}

// Try removiig that handle
private void handleNullPointerException() {
  moveTitans();
  weaponsAttackTitans();
  titansAttack();
  addTitansToLane();
  updateTexts();
}

private void handleGameOver() {
  try {
      isGameOverDisplay();
      Lane1Weapons = new ArrayList<>();
      Lane2Weapons = new ArrayList<>();
      Lane3Weapons = new ArrayList<>();
      Lane4Weapons = new ArrayList<>();
      Lane5Weapons = new ArrayList<>();
  } catch (IOException e) {
      e.printStackTrace();
  }
}


  public void weaponsAttackTitans() {
    //Iterates over the all the weapons Guis and performs the attack of weapons on the titans only in the GUI 
    for (int i = 0; i < Lane1Weapons.size(); i++) {
      WeaponsGUI currentWeapon = Lane1Weapons.get(i);
      if (currentWeapon.getweaponCode() != 4 && Lane1WeaponsStatus.get(i) ) {
        AnchorPane ball = currentWeapon.getBallPane();
        try {
          if (titanImages.get(0).size() != 0) {
            Lane1Pane.getChildren().add(ball);
            Lane1Pane.setLeftAnchor(ball, 125 + currentWeapon.getWidth() + 10.0);
            Lane1Pane.setTopAnchor(ball, 50.0);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(ball);
            double res = 125 + currentWeapon.getWidth() + 10.0;
            if (res < 700) {
              transition.setToX(titanImages.get(0).get(0).getpos() - res);
              transition.setDuration(Duration.millis(1000));
            } else {
              transition.setToX(648);
              transition.setDuration(Duration.millis(100));
            }
            transition.setDuration(Duration.millis(1000));
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), ball);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            ParallelTransition parallelTransition = new ParallelTransition(transition, fadeOut);
            parallelTransition.setOnFinished(event -> Lane1Pane.getChildren().remove(ball));
            parallelTransition.play();
          }

        } catch (Exception e) {
          System.out.println("Duplicate kid i guess");
        }
      }
    }

    for (int i = 0; i < Lane2Weapons.size(); i++) {
      WeaponsGUI currentWeapon = Lane2Weapons.get(i);
      if (currentWeapon.getweaponCode() != 4  && Lane2WeaponsStatus.get(i)) {
        AnchorPane ball = currentWeapon.getBallPane();
        try {
          if (titanImages.get(1).size() != 0) {
            Lane2Pane.getChildren().add(ball);
            Lane2Pane.setLeftAnchor(ball, 125 + currentWeapon.getWidth() + 10.0);
            Lane2Pane.setTopAnchor(ball, 50.0);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(ball);
            double res = 125 + currentWeapon.getWidth() + 10.0;
            if (res < 700) {
              transition.setToX(titanImages.get(1).get(0).getpos() - res);
              transition.setDuration(Duration.millis(1000));
            } else {
              transition.setToX(648);
              transition.setDuration(Duration.millis(100));
            }
            transition.setDuration(Duration.millis(1000));
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), ball);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            ParallelTransition parallelTransition = new ParallelTransition(transition, fadeOut);
            parallelTransition.setOnFinished(event -> Lane2Pane.getChildren().remove(ball));
            parallelTransition.play();
          }

        } catch (Exception e) {
          System.out.println("Duplicate kid i guess");
        }
      }
    }

    for (int i = 0; i < Lane3Weapons.size(); i++) {
      WeaponsGUI currentWeapon = Lane3Weapons.get(i);
      if (currentWeapon.getweaponCode() != 4 && Lane3WeaponsStatus.get(i)) {
        AnchorPane ball = currentWeapon.getBallPane();
        try {
          if (titanImages.get(2).size() != 0) {
            Lane3Pane.getChildren().add(ball);
            Lane3Pane.setLeftAnchor(ball, 125 + currentWeapon.getWidth() + 10.0);
            Lane3Pane.setTopAnchor(ball, 50.0);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(ball);
            double res = 125 + currentWeapon.getWidth() + 10.0;
            if (res < 700) {
              transition.setToX(titanImages.get(2).get(0).getpos() - res);
              transition.setDuration(Duration.millis(1000));
            } else {
              transition.setToX(648);
              transition.setDuration(Duration.millis(100));
            }
            transition.setDuration(Duration.millis(1000));
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), ball);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            ParallelTransition parallelTransition = new ParallelTransition(transition, fadeOut);
            parallelTransition.setOnFinished(event -> Lane3Pane.getChildren().remove(ball));
            parallelTransition.play();
          }

        } catch (Exception e) {
          System.out.println("Duplicate kid i guess");
        }
      }
    }

    for (int i = 0; i < Lane4Weapons.size(); i++) {
      WeaponsGUI currentWeapon = Lane4Weapons.get(i);
      if (currentWeapon.getweaponCode() != 4 && Lane4WeaponsStatus.get(i)) {
        AnchorPane ball = currentWeapon.getBallPane();
        try {
          if (titanImages.get(3).size() != 0) {
            Lane4Pane.getChildren().add(ball);
            Lane4Pane.setLeftAnchor(ball, 125 + currentWeapon.getWidth() + 10.0);
            Lane4Pane.setTopAnchor(ball, 50.0);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(ball);
            double res = 125 + currentWeapon.getWidth() + 10.0;
            if (res < 700) {
              transition.setToX(titanImages.get(3).get(0).getpos() - res);
              transition.setDuration(Duration.millis(1000));
            } else {
              transition.setToX(648);
              transition.setDuration(Duration.millis(100));
            }
            transition.setDuration(Duration.millis(1000));
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), ball);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            ParallelTransition parallelTransition = new ParallelTransition(transition, fadeOut);
            parallelTransition.setOnFinished(event -> Lane4Pane.getChildren().remove(ball));
            parallelTransition.play();
          }

        } catch (Exception e) {
          System.out.println("Duplicate kid i guess");
        }
      }
    }

    for (int i = 0; i < Lane5Weapons.size(); i++) {
      WeaponsGUI currentWeapon = Lane5Weapons.get(i);
      if (currentWeapon.getweaponCode() != 4 && Lane5WeaponsStatus.get(i)) {
        AnchorPane ball = currentWeapon.getBallPane();
        try {
          if (titanImages.get(4).size() != 0) {
            Lane5Pane.getChildren().add(ball);
            Lane5Pane.setLeftAnchor(ball, 125 + currentWeapon.getWidth() + 10.0);
            Lane5Pane.setTopAnchor(ball, 50.0);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(ball);
            double res = 125 + currentWeapon.getWidth() + 10.0;
            if (res < 700) {
              transition.setToX(titanImages.get(4).get(0).getpos() - res);
              transition.setDuration(Duration.millis(1000));
            } else {
              transition.setToX(648);
              transition.setDuration(Duration.millis(100));
            }

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), ball);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            ParallelTransition parallelTransition = new ParallelTransition(transition, fadeOut);
            parallelTransition.setOnFinished(event -> Lane5Pane.getChildren().remove(ball));
            parallelTransition.play();
          }

        } catch (Exception e) {
          System.out.println("Duplicate kid i guess");
        }
      }
    }
    
        int rows = titanImages.size();
for (int i = 0; i < rows; i++) {
    List<TitanGUI> titanRow = titanImages.get(i);
    Iterator<TitanGUI> iterator = titanRow.iterator();
    while (iterator.hasNext()) {
        TitanGUI currentTitan = iterator.next();
        currentTitan.takeDamage();
        if (currentTitan.isDead()) {
            iterator.remove();
            Pane lanePane;
            switch (i) {
                case 0:
                    lanePane = Lane1Pane;
                    break;
                case 1:
                    lanePane = Lane2Pane;
                    break;
                case 2:
                    lanePane = Lane3Pane;
                    break;
                case 3:
                    lanePane = Lane4Pane;    
                    break;
                case 4:
                    lanePane = Lane5Pane;    
                    break;    
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            lanePane.getChildren().remove(currentTitan.getPane());
        }
    }
}

  }

  public void titansAttack() {
    ArrayList < Lane > currentLanes = battle.getOriginalLanes();
    for (int i = 0; i < currentLanes.size(); i++) {
      for (int j = 0; j < titanImages.get(i).size(); j++)
        if (!titanImages.get(i).isEmpty() && titanImages.get(i).get(j) != null)
          titanImages.get(i).get(j).attack();

      System.out.println((i + 1) + " " + currentLanes.get(i).getLaneWall().getCurrentHealth());
      switch (i) {
      case 0:
        wall1Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth() + 0.0) / currentLanes.get(i).getLaneWall().getBaseHealth());
        break;
      case 1:
        wall2Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth() + 0.0) / currentLanes.get(i).getLaneWall().getBaseHealth());
        break;
      case 2:
        wall3Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth() + 0.0) / currentLanes.get(i).getLaneWall().getBaseHealth());
        break;
      case 3:
        wall4Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth() + 0.0) / currentLanes.get(i).getLaneWall().getBaseHealth());
        break;
      case 4:
        wall5Bar.setProgress((currentLanes.get(i).getLaneWall().getCurrentHealth() + 0.0) / currentLanes.get(i).getLaneWall().getBaseHealth());
        break;
      }

      if (currentLanes.get(i).isLaneLost()) {

        switch (i + 1) {
        case 1:
          Wall1.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
          break;
        case 2:
          Wall2.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
          break;
        case 3:
          Wall31.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
          break;
        case 4:
          Wall4.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
          break;
        case 5:
          Wall51.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
          break;
        }

        if (i + 1 == 3)
          Wall32.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
        if (i + 1 == 5) {
          Wall52.setImage(new Image(getClass().getResource("assets/damagedWall.png").toString()));
          Wall52.setFitWidth(94.0);
          Wall52.setFitHeight(199.0);
        }

        switch (i + 1) {
        case 1:
          Lane1Pane.getChildren().removeAll(Lane1Pane.getChildren());
          break;
        case 2:
          Lane2Pane.getChildren().removeAll(Lane2Pane.getChildren());
          break;
        case 3:
          Lane3Pane.getChildren().removeAll(Lane3Pane.getChildren());
          break;
        case 4:
          Lane4Pane.getChildren().removeAll(Lane4Pane.getChildren());
          break;
        case 5:
          Lane5Pane.getChildren().removeAll(Lane5Pane.getChildren());
          break;
        }

      }

    }
    //Update titans take attack 

  }

  public void skipTurn(ActionEvent event) {
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

    if (battle.isGameOver()) {
      try {
        isGameOverDisplay();
        Lane1Weapons = new ArrayList < WeaponsGUI > ();
        Lane2Weapons = new ArrayList < WeaponsGUI > ();
        Lane3Weapons = new ArrayList < WeaponsGUI > ();
        Lane4Weapons = new ArrayList < WeaponsGUI > ();
        Lane5Weapons = new ArrayList < WeaponsGUI > ();

      } catch (IOException e) {

      }
    }else{
      passTurnButton.setDisable(true);
      //passTurnButton.setOpacity(1);
      new java.util.Timer().schedule( 
        new java.util.TimerTask() {
          @Override
            public void run() {
              // Enable the button
              passTurnButton.setDisable(false);
            }
        }, 
      1000);

    }

  }

  public void isGameOverDisplay() throws IOException {
    System.out.println("Works");

    ((Label) gameOverRoot.getChildrenUnmodifiable().get(1)).setText("" + battle.getScore());
    MainParent.getChildren().add(gameOverRoot);
    AnchorPane.setTopAnchor(gameOverRoot, 240.0);
    AnchorPane.setLeftAnchor(gameOverRoot, 460.0);

    passTurnButton.setDisable(true);
    SettingsButton.setDisable(true);
    purchasePiercingButton.setDisable(true);
    purchaseSniperButton.setDisable(true);
    purchaseTrapButton.setDisable(true);
    purchaseVolleyButton.setDisable(true);

  }

  public void showPauseMenu(ActionEvent event) {
    MainParent.getChildren().add(pauseMenuRoot);
    AnchorPane.setTopAnchor(pauseMenuRoot, 240.0);
    AnchorPane.setLeftAnchor(pauseMenuRoot, 760.0);

  }

}