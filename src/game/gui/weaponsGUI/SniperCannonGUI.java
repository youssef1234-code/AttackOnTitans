package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
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
    private double weaponLeftDistance;
    private double width;
    private int count;
    private Label NumberOfWeapons;
    public SniperCannonGUI(Weapon sniperCannonObject) {
        
        this.weaponLeftDistance = 0;
        this.sniperCannonObject = sniperCannonObject;
        sniperCannonPane.setPrefWidth(115);
        sniperCannonPane.setPrefHeight(127);
        this.width = 115;
        sniperCannonImage = new ImageView(getClass().getResource("../assets/SniperCannon.png").toString());
        sniperCannonImage.setFitWidth(115);
        sniperCannonImage.setFitHeight(75);
    
        sniperCannonBallPane.setPrefSize(25, 25);
        ball = new ImageView(getClass().getResource("../assets/SniperCannonBall.png").toString());
        ball.setFitWidth(25);
        ball.setFitHeight(25);
        sniperCannonBallPane.getChildren().add(ball);

    
        sniperCannonPane.getChildren().add(sniperCannonImage);
        
        count = 1;
        NumberOfWeapons = new Label(count+""); 
        NumberOfWeapons.setStyle("-fx-text-fill: #631E1E; -fx-font-family: Oswald; -fx-font-size: 20;");
        sniperCannonPane.getChildren().add(NumberOfWeapons);
        AnchorPane.setTopAnchor(NumberOfWeapons, 60.0);
        AnchorPane.setLeftAnchor(NumberOfWeapons, 30.0);
    }
    @Override
    public void increaseCount(){
        this.count ++;
        NumberOfWeapons.setText(count+"");
    }
    
    @Override
    public AnchorPane getPane(){
        return this.sniperCannonPane;        
    }

 

    @Override
    public AnchorPane getBallPane(){
        AnchorPane res = new AnchorPane();
        res.setPrefSize(25, 25);
        ball = new ImageView(getClass().getResource("../assets/SniperCannonBall.png").toString());
        ball.setFitWidth(25);
        ball.setFitHeight(25);
        res.getChildren().add(ball);
        return res;
    }

    @Override
    public int getweaponCode(){
        return this.weaponCode;
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
