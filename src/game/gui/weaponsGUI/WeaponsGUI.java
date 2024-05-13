package game.gui.weaponsGUI;

import javafx.scene.layout.AnchorPane;

public abstract class WeaponsGUI {
    public abstract AnchorPane getPane();
    public abstract int getweaponCode();
    public abstract AnchorPane getBallPane();
    public abstract void setLeftAnchorDistanceInPixels(double weaponLeftDistance);
    public abstract double getWidth();

}
