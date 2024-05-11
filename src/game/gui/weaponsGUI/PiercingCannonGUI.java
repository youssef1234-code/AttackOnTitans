package game.gui.weaponsGUI;
import game.engine.weapons.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PiercingCannonGUI extends WeaponsGUI{

    private final int weaponCode = 1;
    private Weapon piercingCannonObject;
    public AnchorPane piercingCannonPane = new AnchorPane();
    //private AnchorPane currentLane;
    private ImageView piercingCannonImage;

    public PiercingCannonGUI(Weapon piercingCannonObject) {
        this.piercingCannonObject = piercingCannonObject;
        piercingCannonPane.setPrefWidth(115);
        piercingCannonPane.setPrefHeight(127);

        piercingCannonImage = new ImageView(getClass().getResource("../assets/PiercingSpreadCannon.png").toString());
        piercingCannonImage.setFitWidth(115);
        piercingCannonImage.setFitHeight(115);

        piercingCannonPane.getChildren().add(piercingCannonImage);
    }

    public void attackTitans(){
        
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
