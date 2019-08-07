package application;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("GUI.fxml"));
	        AnchorPane root = loader.load();
			Scene scene = new Scene(root,1171,576);
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
		closeConfirmation.setX(mainStage.getX());
		closeConfirmation.setY(mainStage.getY());
		Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
		if (!ButtonType.OK.equals(closeResponse.get())) {
			event.consume();
		}
	};
}
