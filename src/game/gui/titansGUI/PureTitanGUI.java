package game.gui.titansGUI;
import game.engine.titans.PureTitan;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
public class PureTitanGUI {

    private PureTitan titanObj;
    public AnchorPane pureTitanView  = new AnchorPane();
    private ProgressBar healthBar;
    private ImageView  sprite;

    public PureTitanGUI(PureTitan titanObj){
        this.titanObj = titanObj;
        pureTitanView.setPrefWidth(90.0);
        pureTitanView.setPrefHeight(100);
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        
        healthBar.setPrefWidth(50);
        healthBar.setPrefHeight(10);
        sprite = new ImageView(getClass().getResource("../assets/Titan3.png").toString());

        pureTitanView.getChildren().addAll(healthBar, sprite);
        sprite.setFitWidth(80);
        sprite.setFitHeight(90);

        AnchorPane.setTopAnchor(sprite, 0.0);
        
    }

    public void takeDamage(){
        healthBar.setProgress(titanObj.getCurrentHealth()/titanObj.getBaseHealth());
    }

    public void translate(){
        AnchorPane.setRightAnchor(pureTitanView, titanObj.getSpeed()*10.0);
    }
    

    

}
