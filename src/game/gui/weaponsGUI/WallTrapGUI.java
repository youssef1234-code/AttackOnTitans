package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class WallTrapGUI extends WeaponsGUI{
    private static final int weaponCode = 4;
    private Weapon wallTrapObject;
    public AnchorPane wallTrapPane = new AnchorPane();
    private ImageView wallTrapImage;
    private double weaponLeftDistance;
    
    public WallTrapGUI(Weapon wallTrapObject) {
        this.weaponLeftDistance = 0;
        this.wallTrapObject = wallTrapObject;
        wallTrapPane.setPrefWidth(50);
        wallTrapPane.setPrefHeight(50);

        wallTrapImage = new ImageView(getClass().getResource("../assets/wallTrap.png").toString());
        wallTrapImage.setFitWidth(43);
        wallTrapImage.setFitHeight(41);
        
        wallTrapPane.getChildren().add(wallTrapImage);
    }

    @Override
    public AnchorPane getPane(){
        return this.wallTrapPane;
    }

    @Override
    public int getweaponCode(){
        return this.weaponCode;
    }
    

    @Override
    public AnchorPane getBallPane(){
        //No attack animation is required
        return wallTrapPane;
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
    @Override 
    public void increaseCount(){}
     

}
