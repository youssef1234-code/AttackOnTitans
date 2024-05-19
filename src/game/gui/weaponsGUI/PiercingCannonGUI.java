package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
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
    private double weaponLeftDistance;
    private int count;
    private Label NumberOfWeapons;
    // private int currentLocation; to get the location of the cannon to start firing from there 

    public PiercingCannonGUI(Weapon piercingCannonObject) {
        this.weaponLeftDistance = 0;
        this.piercingCannonObject = piercingCannonObject;
        piercingCannonPane.setPrefWidth(115);
        piercingCannonPane.setPrefHeight(127);
        piercingCannonImage = new ImageView(getClass().getResource("../assets/PiercingSpreadCannon.png").toString());
        piercingCannonImage.setFitWidth(115);
        piercingCannonImage.setFitHeight(115);

        piercingCannonBallPane.setPrefSize(190, 25);
        ball = new ImageView(getClass().getResource("../assets/Arrow.png").toString());
        ball.setFitWidth(90);
        ball.setFitHeight(25);

        piercingCannonBallPane.getChildren().add(ball);
        piercingCannonPane.getChildren().add(piercingCannonImage);

        count = 1;
        NumberOfWeapons = new Label(count + "");
        NumberOfWeapons.setStyle("-fx-text-fill: #631E1E; -fx-font-family: Oswald; -fx-font-size: 20;");

        piercingCannonPane.getChildren().add(NumberOfWeapons);
        AnchorPane.setTopAnchor(NumberOfWeapons, 80.0);
        AnchorPane.setLeftAnchor(NumberOfWeapons, 30.0);

    }
    public void increaseCount(){
        this.count ++;
        NumberOfWeapons.setText(count+"");
    }



    @Override
    public AnchorPane getBallPane(){
        AnchorPane res = new AnchorPane();
        res.setPrefSize(190, 25);
        ball = new ImageView(getClass().getResource("../assets/Arrow.png").toString());
        ball.setFitWidth(90);
        ball.setFitHeight(25);
        res.getChildren().add(ball);
        return res;
    }

    @Override
    public AnchorPane getPane(){
        return this.piercingCannonPane;
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
