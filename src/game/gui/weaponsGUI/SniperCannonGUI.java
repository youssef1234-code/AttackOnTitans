package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SniperCannonGUI extends WeaponsGUI{
    
    private static final int weaponCode = 2;
    private Weapon sniperCannonObject;
    public AnchorPane sniperCannonPane = new AnchorPane();
    //private AnchorPane currentLane;
    private ImageView sniperCannonImage;

    public SniperCannonGUI(Weapon sniperCannonObject) {
        this.sniperCannonObject = sniperCannonObject;
        sniperCannonPane.setPrefWidth(115);
        sniperCannonPane.setPrefHeight(127);

        sniperCannonImage = new ImageView(getClass().getResource("../assets/SniperCannon.png").toString());
        sniperCannonImage.setFitWidth(115);
        sniperCannonImage.setFitHeight(75);

    
        sniperCannonPane.getChildren().add(sniperCannonImage);
    }
    @Override
    public AnchorPane getPane(){
        return this.sniperCannonPane;
    }

    @Override
    public int getweaponCode(){
        return this.weaponCode;
    }

    public void attackTitans(){
        
    }
    

}
