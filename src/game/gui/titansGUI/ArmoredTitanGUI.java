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
    private int StepsCount = 0;
    private int MAX_STEPSCOUNT = 5;

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

        armoredTitanView.getChildren().addAll(healthBar, sprite);

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
            transition.setNode(armoredTitanView);
            transition.setToX(armoredTitanView.getTranslateX() - titanObj.getSpeed()*40.0);
            transition.setDuration(Duration.millis(1000)); 
            transition.play();
            StepsCount++;
            System.out.println(StepsCount);
        }
        else    
            System.out.println("Beside Wall");
        
}
}
