package game.gui.titansGUI;

import game.engine.titans.AbnormalTitan;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AbnormalTitanGUI extends TitanGUI{

    private AbnormalTitan titanObj;
    public  AnchorPane abnormalTitanView  = new AnchorPane();
    private ProgressBar healthBar;
    private ImageView  sprite;


    public AbnormalTitanGUI(AbnormalTitan titanObj){
        this.titanObj = titanObj;
        abnormalTitanView.setPrefWidth(70.0);
        abnormalTitanView.setPrefHeight(80);
        
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        healthBar.setPrefWidth(30); 
        healthBar.setPrefHeight(10);
    
        
        
        sprite = new ImageView(getClass().getResource("../assets/Titan2.png").toString());
        sprite.setFitWidth(60);
        sprite.setFitHeight(70);
        //sprite.setRotate(90.0);

        abnormalTitanView.getChildren().addAll(sprite, healthBar);

        AnchorPane.setTopAnchor(healthBar, 0.0);
        AnchorPane.setLeftAnchor(healthBar, 5.0);
        AnchorPane.setRightAnchor(healthBar, 5.0);
        AnchorPane.setTopAnchor(sprite, 0.0);

    }

    public void takeDamage(){
        healthBar.setProgress( (double)titanObj.getCurrentHealth()/titanObj.getBaseHealth());
    }

    public void translate(){
        if(abnormalTitanView.getLayoutX() + abnormalTitanView.getTranslateX() > 700){
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(abnormalTitanView);
            transition.setToX(abnormalTitanView.getTranslateX() - titanObj.getSpeed()*24.5);
            transition.setDuration(Duration.millis(750)); 
            transition.play();
        }
        //else    
        //    System.out.println("Beside Wall");
        
}
    @Override
    public double getpos(){
        return abnormalTitanView.getLayoutX() + abnormalTitanView.getTranslateX()  ;
    }
    @Override 
    public AnchorPane getPane(){
        return abnormalTitanView;
    }
    @Override 
    public boolean isDead(){
        return this.titanObj.isDefeated();
    }
}
