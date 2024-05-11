package game.gui.titansGUI;
import game.engine.titans.PureTitan;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
public class PureTitanGUI {

    private PureTitan titanObj;
    public AnchorPane pureTitanView  = new AnchorPane();
    private ProgressBar healthBar;
    private ImageView  sprite;
    private int StepsCount = 0;
    private int MAX_STEPSCOUNT = 22;

    public PureTitanGUI(PureTitan titanObj) {
        this.titanObj = titanObj;
        pureTitanView.setPrefWidth(90.0);
        pureTitanView.setPrefHeight(100);
        
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        healthBar.setPrefWidth(50); 
        healthBar.setPrefHeight(10);
    
        
        
        sprite = new ImageView(getClass().getResource("../assets/Titan3.png").toString());
        sprite.setFitWidth(80);
        sprite.setFitHeight(90);

        pureTitanView.getChildren().addAll(healthBar, sprite);

        AnchorPane.setTopAnchor(healthBar, 5.0);
        AnchorPane.setLeftAnchor(healthBar, 5.0);
        AnchorPane.setRightAnchor(healthBar, 5.0);
        AnchorPane.setTopAnchor(sprite, 0.0);
    }

    public void takeDamage(){
        healthBar.setProgress(titanObj.getCurrentHealth()/titanObj.getBaseHealth());
    }

    public void translate(){
        if(StepsCount<MAX_STEPSCOUNT){
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(pureTitanView);
            transition.setToX(pureTitanView.getTranslateX() - titanObj.getSpeed()*10.0);
            transition.setDuration(Duration.millis(1000)); 
            transition.play();
            StepsCount++;
            System.out.println(StepsCount);
        }
        else    
            System.out.println("Beside Wall");
        
}
    

    

}
