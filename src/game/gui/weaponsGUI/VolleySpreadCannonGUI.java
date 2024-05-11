package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class VolleySpreadCannonGUI extends WeaponsGUI{
    private static final int weaponCode = 3;
    private Weapon volleySpredObject;
    public AnchorPane volleySpredCannonPane = new AnchorPane();
    private ImageView volleySpreadImage;

    public VolleySpreadCannonGUI(Weapon volleySpredObject) {
        this.volleySpredObject = volleySpredObject;
        volleySpredCannonPane.setPrefWidth(115);
        volleySpredCannonPane.setPrefHeight(127);

        volleySpreadImage = new ImageView(getClass().getResource("../assets/VolleyCannon.png").toString());
        volleySpreadImage.setFitWidth(115);
        volleySpreadImage.setFitHeight(75);

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

    public void attackTitans(){
        
    }
    

}
