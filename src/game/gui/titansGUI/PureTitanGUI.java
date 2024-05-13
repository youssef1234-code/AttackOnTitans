package game.gui.titansGUI;

import game.engine.titans.PureTitan;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class PureTitanGUI extends TitanGUI{

    private PureTitan titanObj;
    public AnchorPane pureTitanView  = new AnchorPane();
    private ProgressBar healthBar;
    private ImageView  sprite;

    public PureTitanGUI(PureTitan titanObj) {
        this.titanObj = titanObj;
        pureTitanView.setPrefWidth(90.0);
        pureTitanView.setPrefHeight(100);
        
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        healthBar.setPrefWidth(30); 
        healthBar.setPrefHeight(10);
    
        
        
        sprite = new ImageView(getClass().getResource("../assets/Titan1.png").toString());
        sprite.setFitWidth(80);
        sprite.setFitHeight(90);
        sprite.setRotate(90.0);

        pureTitanView.getChildren().addAll(sprite, healthBar);

        AnchorPane.setTopAnchor(healthBar, 0.0);
        AnchorPane.setLeftAnchor(healthBar, 5.0);
        AnchorPane.setRightAnchor(healthBar, 5.0);
        AnchorPane.setTopAnchor(sprite, 0.0);
    }

    public void takeDamage(){
        healthBar.setProgress( (double) titanObj.getCurrentHealth()/titanObj.getBaseHealth());
    }

    public void translate(){ // 
        if(pureTitanView.getLayoutX() + pureTitanView.getTranslateX() > 700){
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(pureTitanView);
            transition.setToX(pureTitanView.getTranslateX() - titanObj.getSpeed()*22);
            transition.setDuration(Duration.millis(1000)); 
            transition.play();
        }
        //else    
        //    System.out.println("Beside Wall");
    }
    @Override
    public double getpos(){
        return pureTitanView.getLayoutX() + pureTitanView.getTranslateX() ;
    }
    
    @Override 
    public AnchorPane getPane(){
        return pureTitanView;
    }
    
    @Override 
    public boolean isDead(){
        return this.titanObj.isDefeated();
    }

}
