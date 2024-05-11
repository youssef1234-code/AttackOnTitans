package game.gui.titansGUI;
import game.engine.titans.ColossalTitan;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ColossalTitanGUI extends TitanGUI {

    private ColossalTitan titanObj;
    public AnchorPane colossalTitanView  = new AnchorPane();
    private ProgressBar healthBar;
    private ImageView  sprite;
    private int StepsCount = 0;
    private int MAX_STEPSCOUNT = 5;

    public ColossalTitanGUI(ColossalTitan titanObj) {
        this.titanObj = titanObj;
        colossalTitanView.setPrefWidth(120.0);
        colossalTitanView.setPrefHeight(150);
        
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        healthBar.setPrefWidth(30); 
        healthBar.setPrefHeight(10);
    
        
        
        sprite = new ImageView(getClass().getResource("../assets/Titan4.png").toString());
        sprite.setFitWidth(80);
        sprite.setFitHeight(90);
        //sprite.setRotate(90.0);

        colossalTitanView.getChildren().addAll(healthBar, sprite);

        AnchorPane.setTopAnchor(healthBar, 0.0);
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
            transition.setNode(colossalTitanView);
            transition.setToX(colossalTitanView.getTranslateX() - titanObj.getSpeed()*35.0);
            transition.setDuration(Duration.millis(1000)); 
            transition.play();
            StepsCount++;
            System.out.println(StepsCount);
        }
        else    
            System.out.println("Beside Wall");
        
}
    

}
