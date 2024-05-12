package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class VolleySpreadCannonGUI extends WeaponsGUI{
    private static final int weaponCode = 3;
    private Weapon volleySpredObject;
    public AnchorPane volleySpredCannonPane = new AnchorPane();
    private ImageView volleySpreadImage;
    public AnchorPane volleyCannonBallPane = new AnchorPane();
    private ImageView ball; 

    public VolleySpreadCannonGUI(Weapon volleySpredObject) {
        this.volleySpredObject = volleySpredObject;
        volleySpredCannonPane.setPrefWidth(115);
        volleySpredCannonPane.setPrefHeight(127);

        volleySpreadImage = new ImageView(getClass().getResource("../assets/VolleyCannon.png").toString());
        volleySpreadImage.setFitWidth(115);
        volleySpreadImage.setFitHeight(75);

        //290*35
        volleyCannonBallPane.setPrefSize(25,25);
        ball = new ImageView(getClass().getResource("../assets/volleyBall.png").toString());
        ball.setFitWidth(25);
        ball.setFitHeight(25);
        volleyCannonBallPane.getChildren().add(ball);

        volleySpredCannonPane.getChildren().add(volleySpreadImage);
    }

    @Override
    public AnchorPane getPane(){
        return this.volleySpredCannonPane;
    }

    @Override
    public int getweaponCode(){
        return this.weaponCode;
    }


    @Override 
    public AnchorPane getBallPane() {
        return volleyCannonBallPane;
    }
    @Override
    public void attackTitans(){
        System.out.println("PERFORMING WEAPON ATTACK !!!");
        //getNearestTitan() should be implemented to get the first titan in the lane where the cannon ball should stop
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(volleyCannonBallPane);
        //Should be upadated to the value of the nearest Titan location
        transition.setToX(1200);
        transition.setDuration(Duration.millis(1000)); 
        transition.play();
    }
    

}
