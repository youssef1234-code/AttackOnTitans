package game.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class NewGameController extends BGMedia implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final String css = getClass().getResource("application.css").toExternalForm();

  
    @FXML
    private AnchorPane newGamePane;
    @FXML
    private Button backButton, muteButton, easyButton, hardButton, instructionsButton;

    @FXML
    private MediaView backgroundVideo;

    //@FXML
    //private StackPane stack;
   



   public void mute(ActionEvent event){
      muteMedia(event, muteButton);
    }

    public void goToMainMenu(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("FXML/MainMenuScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        Button nextMuteButton = (Button) root.lookup("#muteButton");
        if(muteButton.getText().equals("Mute"))
            nextMuteButton.setText("Mute");
        else 
            nextMuteButton.setText("Unmute");


        stage.setScene(scene);
        scene.setOnKeyPressed(ev ->{
            if(ev.getCode() == KeyCode.F11)
                stage.setFullScreen(!stage.isFullScreen());
        });
        stage.setResizable(false);
        stage.setFullScreen(true);

        stage.show();

    }
    public void initializeGame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("FXML/GameScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        scene.setOnKeyPressed(ev ->{
            if(ev.getCode() == KeyCode.F11)
                stage.setFullScreen(!stage.isFullScreen());
        });
        stage.setResizable(false);
        stage.setFullScreen(true);
        muteNoText(event);
        stage.show();

    }
    public void initializeEasyGame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("FXML/EasyGameScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        scene.setOnKeyPressed(ev ->{
            if(ev.getCode() == KeyCode.F11)
                stage.setFullScreen(!stage.isFullScreen());
        });
        stage.setResizable(false);
        stage.setFullScreen(true);
        muteNoText(event);
        stage.show();

    }
    

    public void showInstructions(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("FXML/InstructionsWindow.fxml"));
        root.getStylesheets().add(css);
       
        newGamePane.getChildren().add(6, root);

        AnchorPane.setTopAnchor(root, 240.0);
        AnchorPane.setRightAnchor(root, 460.0);
     

    }

    public void initialize(URL location, ResourceBundle resources){
       resumeMedia(backgroundVideo);
    }


}
