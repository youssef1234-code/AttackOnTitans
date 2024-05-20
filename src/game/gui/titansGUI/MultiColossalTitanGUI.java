package game.gui.titansGUI;

import java.util.ArrayList;

import game.engine.titans.ColossalTitan;
import game.engine.titans.Titan;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MultiColossalTitanGUI extends ColossalTitanGUI {

    private Label numOfTitans;
    private ArrayList<ColossalTitan> titans; 
    private int numOfLeft;

    public MultiColossalTitanGUI (ArrayList<ColossalTitan> titans){
        super(titans.get(0));
        this.titans = titans;
        
        numOfTitans = new Label("" + titans.size());
        numOfTitans.setStyle("-fx-text-fill: #631E1E; -fx-font-family: Oswald; -fx-font-size: 20;");

        colossalTitanView.getChildren().add(numOfTitans);
        AnchorPane.setLeftAnchor(numOfTitans, 80.0);
        AnchorPane.setTopAnchor(numOfTitans, 10.0);

        numOfLeft = titans.size();
        
    }

    public void takeDamage(){
        int sumCH= 0;
        int sumBH= titans.size() * titans.get(0).getBaseHealth();
        for(ColossalTitan titan : titans){
            sumCH += titan.getCurrentHealth();
            if(titan.getCurrentHealth() == 0)
                numOfTitans.setText(numOfLeft-- + "");
        }
        //System.out.println(numOfLeft);

        getBar().setProgress( (double) sumCH/sumBH);


    }
    
}
