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
    private double weaponLeftDistance;
    private double width ; 
    public VolleySpreadCannonGUI(Weapon volleySpredObject) {
        this.weaponLeftDistance = 0;
        this.volleySpredObject = volleySpredObject;
        volleySpredCannonPane.setPrefWidth(115);
        volleySpredCannonPane.setPrefHeight(127);

        volleySpreadImage = new ImageView(getClass().getResource("../assets/VolleyCannon.png").toString());
        volleySpreadImage.setFitWidth(115);
        volleySpreadImage.setFitHeight(75);
        this.width = 115;

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
        AnchorPane res = new AnchorPane();
        res.setPrefSize(35, 35);
        ball = new ImageView(getClass().getResource("../assets/volleyBall.png").toString());
        ball.setFitWidth(35);
        ball.setFitHeight(35);
        res.getChildren().add(ball);
        return res;
    }


    @Override
    public void setLeftAnchorDistanceInPixels(double weaponLeftDistance){
        this.weaponLeftDistance = weaponLeftDistance;
    }

    public double getLeftAnchorDistanceInPixels(){
        return this.weaponLeftDistance;
    }
    @Override
    public double getWidth(){
        return this.weaponLeftDistance;
    }
     

}
