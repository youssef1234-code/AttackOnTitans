package game.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException { 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuScene.fxml"));
        Parent root = loader.load();
        //<Controller Class Name> controller = loader.getController();
        Scene scene = new Scene(root);
        
        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.F11)
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
        });
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Press F11 to remove Full-Screen");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Attack On Titans - Utopia");

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            exit(primaryStage); 
        });
        
        Image icon = new Image(getClass().getResourceAsStream("icon.jpg"));
        primaryStage.getIcons().add(icon);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.show();
    } 

    public void exit(Stage stage){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're about to Quit!!");
        alert.setContentText("You will lose all your progress if you quit !!");
        if(alert.showAndWait().get() == ButtonType.OK){
                System.out.println("You have successfully closed the window");
                stage.close();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
 