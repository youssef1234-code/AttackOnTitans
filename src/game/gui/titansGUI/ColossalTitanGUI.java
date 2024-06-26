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


    public ColossalTitanGUI(ColossalTitan titanObj) {
        this.titanObj = titanObj;
        colossalTitanView.setPrefWidth(120.0);
        colossalTitanView.setPrefHeight(150);
        
        healthBar = new ProgressBar(1);
        healthBar.setMinHeight(0);
        healthBar.setPrefWidth(30); 
        healthBar.setPrefHeight(10);
    
        
        
        sprite = new ImageView(getClass().getResource("../assets/Titan4.png").toString());
        sprite.setFitWidth(110);
        sprite.setFitHeight(140);
        sprite.setRotate(90.0);

        colossalTitanView.getChildren().addAll(sprite, healthBar);

        AnchorPane.setTopAnchor(healthBar, 0.0);
        AnchorPane.setLeftAnchor(healthBar, 5.0);
        AnchorPane.setRightAnchor(healthBar, 5.0);
        AnchorPane.setTopAnchor(sprite, 0.0);
    }

    public void takeDamage(){
        healthBar.setProgress( (double) titanObj.getCurrentHealth()/titanObj.getBaseHealth());
    }

    public void translate(){
        if(colossalTitanView.getLayoutX() + colossalTitanView.getTranslateX() > 700){
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(colossalTitanView);
            transition.setToX(colossalTitanView.getTranslateX() - titanObj.getSpeed()*16.8);
            transition.setDuration(Duration.millis(1000)); 
            transition.play();
        }
        //else    
        //   System.out.println("Beside Wall");
        
}
    @Override
    public double getpos(){
        return colossalTitanView.getLayoutX() + colossalTitanView.getTranslateX() ;
    }
    @Override 
    public AnchorPane getPane(){
        return colossalTitanView;
    }
    @Override 
    public boolean isDead(){
        return this.titanObj.isDefeated();
    }
    
    public ProgressBar getBar(){
        return healthBar;
    }
}
