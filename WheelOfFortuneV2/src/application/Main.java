package application;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * 
 * CLASS: Main
 * 
 * PURPOSE: This class is used to run the GUI. Along providing an in-game method through which the player can exit the game.
 * 
 * PRIVATE VARIABLES: Stage variable. 
 * 
 */


public class Main extends Application {
	private Stage mainStage;
	private final int requestOnExitX = 700;
	private final int requestOnExitY = 350;
	/**
	 * 	 
	 * This method is used to actually start the game by
	 * creating the GUI. 
	 *
	 * 
	 * @param Stage
	 * 
	 * @returns none
	 * 
	 */
	
	public void start(Stage primaryStage) {
		try {
			//sources: 
			//https://stackoverflow.com/questions/10275841/how-to-change-the-icon-on-the-title-bar-of-a-stage-in-java-fx-2-0-of-my-applicat
			//
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("GUI.fxml"));
	        AnchorPane root = loader.load();
			Scene scene = new Scene(root,1171,576);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
			primaryStage.setTitle("Wheel Of Fortune");
			primaryStage.setOnCloseRequest(confirmCloseEventHandler);
			primaryStage.setScene(scene);
			primaryStage.show();
			mainStage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
		//sources:
		Alert closeConfirmation = new Alert(
			Alert.AlertType.CONFIRMATION,
			"Are you sure you want to exit?"
	    );
	    Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
	    	ButtonType.OK
	        );
		exitButton.setText("Exit");
		closeConfirmation.setHeaderText("Confirm Exit");
		closeConfirmation.initModality(Modality.APPLICATION_MODAL);
		closeConfirmation.initOwner(mainStage);
		closeConfirmation.setX(requestOnExitX);
		closeConfirmation.setY(requestOnExitY);
		Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
		if (!ButtonType.OK.equals(closeResponse.get())) {
			event.consume();
		}
	};
}
