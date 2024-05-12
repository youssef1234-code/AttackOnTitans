package game.gui.weaponsGUI;

import javafx.scene.layout.AnchorPane;

public abstract class WeaponsGUI {
    public abstract AnchorPane getPane();
    public abstract int getweaponCode();
    public abstract void attackTitans();
    public abstract AnchorPane getBallPane();
}
