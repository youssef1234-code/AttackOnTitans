package game.gui.titansGUI;

import javafx.scene.layout.AnchorPane;

abstract public class TitanGUI{

    abstract public void translate();
    abstract public void takeDamage();
    abstract public AnchorPane getPane();
    public void attack(){};
    public abstract double getpos();
    public abstract boolean isDead();

}