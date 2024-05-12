package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class PiercingCannonGUI extends WeaponsGUI{

    private final int weaponCode = 1;
    private Weapon piercingCannonObject;
    public AnchorPane piercingCannonPane = new AnchorPane();
    private ImageView piercingCannonImage;
    public AnchorPane piercingCannonBallPane = new AnchorPane();
    private ImageView ball; 
    // private int currentLocation; to get the location of the cannon to start firing from there 

    public PiercingCannonGUI(Weapon piercingCannonObject) {
        this.piercingCannonObject = piercingCannonObject;
        piercingCannonPane.setPrefWidth(115);
        piercingCannonPane.setPrefHeight(127);

        piercingCannonImage = new ImageView(getClass().getResource("../assets/PiercingSpreadCannon.png").toString());
        piercingCannonImage.setFitWidth(115);
        piercingCannonImage.setFitHeight(115);

        piercingCannonBallPane.setPrefSize(190, 25);
        ball = new ImageView(getClass().getResource("../assets/Arrow.png").toString());
        ball.setFitWidth(190);
        ball.setFitHeight(25);

        
        piercingCannonBallPane.getChildren().add(ball);
        piercingCannonPane.getChildren().add(piercingCannonImage);
    }

    @Override
    public void attackTitans(){
    
        //getNearestTitan() should be implemented to get the first titan in the lane where the cannon ball should stop
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(piercingCannonBallPane);
        //Should be upadated to the value of the nearest Titan location
        transition.setToX(1200);
        transition.setDuration(Duration.millis(1000)); 
        transition.play();

    }

    @Override
    public AnchorPane getBallPane(){
        return piercingCannonBallPane;
    }

    @Override
    public AnchorPane getPane(){
        return this.piercingCannonPane;
    }

    @Override
    public int getweaponCode(){
        return this.weaponCode;
    }
}
