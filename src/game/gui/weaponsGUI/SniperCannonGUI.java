package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SniperCannonGUI extends WeaponsGUI{
    
    private static final int weaponCode = 2;
    private Weapon sniperCannonObject;
    public AnchorPane sniperCannonPane = new AnchorPane();
    private ImageView sniperCannonImage;
    public AnchorPane sniperCannonBallPane = new AnchorPane();
    private ImageView ball; 



    public SniperCannonGUI(Weapon sniperCannonObject) {
        this.sniperCannonObject = sniperCannonObject;
        sniperCannonPane.setPrefWidth(115);
        sniperCannonPane.setPrefHeight(127);

        sniperCannonImage = new ImageView(getClass().getResource("../assets/SniperCannon.png").toString());
        sniperCannonImage.setFitWidth(115);
        sniperCannonImage.setFitHeight(75);

        sniperCannonBallPane.setPrefSize(25, 25);
        ball = new ImageView(getClass().getResource("../assets/SniperCannonBall.png").toString());
        ball.setFitWidth(25);
        ball.setFitHeight(25);
        sniperCannonBallPane.getChildren().add(ball);

    
        sniperCannonPane.getChildren().add(sniperCannonImage);
    }

    @Override
    public AnchorPane getBallPane(){
        return this.sniperCannonBallPane;
    }


    @Override
    public AnchorPane getPane(){
        return this.sniperCannonPane;
    }

    @Override
    public int getweaponCode(){
        return this.weaponCode;
    }

    @Override
    public void attackTitans(){
        System.out.println("Attakcing Titans!!!");
         //getNearestTitan() should be implemented to get the first titan in the lane where the cannon ball should stop
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sniperCannonBallPane);
        //Should be upadated to the value of the nearest Titan location
        transition.setToX(1200);
        transition.setDuration(Duration.millis(1000)); 
        transition.play();
    }
    

}
