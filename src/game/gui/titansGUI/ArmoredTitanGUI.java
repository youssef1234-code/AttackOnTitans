package game.gui.titansGUI;

import game.engine.titans.ArmoredTitan;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ArmoredTitanGUI extends TitanGUI{

    private ArmoredTitan titanObj;
    public AnchorPane armoredTitanView  = new AnchorPane();
    private ProgressBar healthBar;
    private ImageView  sprite;


    public ArmoredTitanGUI(ArmoredTitan titanObj){
        this.titanObj = titanObj;
        armoredTitanView.setPrefWidth(70.0);
        armoredTitanView.setPrefHeight(80);
        
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        healthBar.setPrefWidth(30); 
        healthBar.setPrefHeight(10);
    
        
        
        sprite = new ImageView(getClass().getResource("../assets/Titan3.png").toString());
        sprite.setFitWidth(60);
        sprite.setFitHeight(70);
        //sprite.setRotate(90.0);

        armoredTitanView.getChildren().addAll(sprite, healthBar);

        AnchorPane.setTopAnchor(healthBar, 0.0);
        AnchorPane.setLeftAnchor(healthBar, 5.0);
        AnchorPane.setRightAnchor(healthBar, 5.0);
        AnchorPane.setTopAnchor(sprite, 0.0);

    }

    public void takeDamage(){
        healthBar.setProgress( (double) titanObj.getCurrentHealth()/titanObj.getBaseHealth());
    }

    public void translate(){
        if(armoredTitanView.getLayoutX() + armoredTitanView.getTranslateX() > 700){
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(armoredTitanView);
            transition.setToX(armoredTitanView.getTranslateX() - titanObj.getSpeed()*22.0);
            transition.setDuration(Duration.millis(1000)); 
            transition.play();
        }
        //else    
        //    System.out.println("Beside Wall");
        
}
    @Override
    public double getpos(){
        return armoredTitanView.getLayoutX() + armoredTitanView.getTranslateX() ;
    }
    @Override 
    public AnchorPane getPane(){
        return armoredTitanView;
    }
    @Override 
    public boolean isDead(){
        return this.titanObj.isDefeated();
    }
}
