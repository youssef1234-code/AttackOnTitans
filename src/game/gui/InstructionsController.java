package game.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class InstructionsController  {

    @FXML
    private Button close;

    public void escape(ActionEvent event){
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    Scene scene = stage.getScene();
    Parent root = scene.getRoot();
    AnchorPane pane = (AnchorPane) root.lookup("#newGamePane");
    pane.getChildren().remove(6);

    }
    
    
}
