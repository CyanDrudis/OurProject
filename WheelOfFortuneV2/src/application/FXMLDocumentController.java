package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.*;

public class FXMLDocumentController {
	Game g = new Game();
	String spoke = "";
    private final int degreesPerSpoke = 15;
    private boolean spinInput = false;
    private boolean gameInitialized = false;
    private int oldSpokeIndex = 0;
    @FXML
    private Label player1Label;

    @FXML
    private Font x3;

    @FXML
    private Label player1MoneyLabel;

    @FXML
    private Button spinClick;

    @FXML
    private Label player2Label;

    @FXML
    private Font x1;

    @FXML
    private Label player3Label;

    @FXML
    private Font x4;

    @FXML
    private TextField textField;

    @FXML
    private Label categoryLabel;

    @FXML
    private Font x5;

    @FXML
    private Label puzzleLabel;

    @FXML
    private Font x2;

    @FXML
    private Label player2MoneyLabel;

    @FXML
    private Label player3MoneyLabel;

    @FXML
    private Label toDisplayLabel;
    
    @FXML
    private ImageView wheelImageView;
    
    @FXML
    private Button newGameButton;
    
    @FXML
    private Label toDisplayPlayerTurn;
    
    @FXML
    private Label player1Total;

    @FXML
    private Label player2Total;

    @FXML
    private Label player3Total;

    @FXML
    void generateNewGame(ActionEvent event) {
			gameInit();
    }
    @FXML
    public void gameInit() {
    	
        try {
			g.newGame();
			g.importWheel();      
			g.setCurrent();
			refresh();
			gameInitialized = true;
			oldSpokeIndex = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
	public void spinWheel() throws InterruptedException{
    	spinClick.setDisable(true);
    	int wheelSize = g.wheel.size();
    	RotateTransition rotate = new RotateTransition(Duration.millis(2000), wheelImageView);
		try{
			while(g.getRandomNumForWheel() - oldSpokeIndex == 0) {//checks if there is a difference in the spoke were on in-case we randomly generate a 0
				  spoke = g.spin();
			  }
		  }
		  catch(IOException ex){
			  System.out.println("IOException, check file location");
		      ex.printStackTrace();
		  }
		  int spokesToTurn = wheelSize - oldSpokeIndex + g.getRandomNumForWheel();
		  double rotationRequired = degreesPerSpoke*spokesToTurn;
		  System.out.println(spoke);//for testing purposes
		  rotate.setOnFinished(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			        spinClick.setDisable(false);
			    }
			});
		  rotate.setByAngle(rotationRequired);
		  rotate.play();
		  oldSpokeIndex = g.getRandomNumForWheel();
	}
	@FXML
	public void refresh() {
		try {
			g.setCurrent();
			puzzleLabel.setText("Puzzle: " + g.getCurrent());
			player1MoneyLabel.setText("$"+g.money[0]);
			player2MoneyLabel.setText("$"+g.money[1]);
			player3MoneyLabel.setText("$"+g.money[2]);
			player1Total.setText("Total: $" + g.players.get(0).getMoney());
			player2Total.setText("Total: $" + g.players.get(1).getMoney());
			player3Total.setText("Total: $" + g.players.get(2).getMoney());;
			int playersTurn = g.whosTurn()+1;
			toDisplayPlayerTurn.setText("Player " + playersTurn + "'s turn.");
			categoryLabel.setText("Category : " + g.getPuzzle());
		} catch (IOException e) {
				System.out.println("IOException, check file location");
			e.printStackTrace();
		} 
		catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}

    @FXML
    public void display(ActionEvent event) throws InterruptedException {
    	if(g.win()) {
    		int playersTurn = g.getHighestBalPlayer() + 1;
    		toDisplayLabel.setText("Player " + playersTurn + " won with a balance of: " + g.players.get(g.getHighestBalPlayer()).getMoney());
    		spinClick.setDisable(true);
    	}
    	else if(gameInitialized) {
	    	if(textField.getText().equals("")) {
	    		toDisplayLabel.setText("Please enter in a letter or guess the puzzle");
		    }else{
		    spinWheel();
		    try{
		    spinInput = true;
		    if(spinInput){
		                spinInput = false;
		                    while(spoke.equals("freespin")){
		                        spinWheel();
		                        refresh();
		                    }    
		                    int playersTurn = g.whosTurn() + 1;
		                    if (spoke.equals("loseaturn")) {
		                        g.loseATurn();
		                        toDisplayLabel.setText("Sorry you lose a turn!");
		                        playersTurn = g.whosTurn() + 1;
		                        refresh();
		                    }else{
		                        if(spoke.equals("bankrupt")){
		                            g.bankrupt();
		                            refresh();
		                        }else{
		                            String input = textField.getText();
		                            char check[] = input.toCharArray();
		                            if(check.length == 1){
		                                char a = check[0];
		                                String outcome = g.inputChar(a);
		                                refresh();
		                                if(outcome.equals("there")) {
		                                	toDisplayLabel.setText("Correct");
		                                    refresh();
		                                } else if (outcome.equals("notThere")){
		                                	toDisplayLabel.setText("Incorrect");
		                                    g.changeTurn();
		                                    refresh();
		                                } else if (outcome.equals("alreadyThere")) {
		                                	toDisplayLabel.setText("Already guessed");
		                                    g.changeTurn();
		                                    refresh();
		                                } else if (outcome.equals("puzzleComplete")){
		                                	g.winPuzzleCounter();
		                                    g.newPuzzle();
		                                    g.setCurrent();
		                                    playersTurn = g.whosTurn() + 1;
		                                    toDisplayLabel.setText("Player " + playersTurn + " solved it!, New Puzzle!");
		                                    refresh();
		                                }
		                                refresh();
		                            }
		                            else{
		                                if(g.checkAns(input)){	
		                                	playersTurn = g.whosTurn() + 1;
		                                	toDisplayLabel.setText("Player " + playersTurn + " solved it!, New Puzzle!");
		                                    g.winPuzzleCounter();
		                                    g.newPuzzle();
		                                    g.setCurrent();
		                                    refresh();
		                                }else{
		                                	toDisplayLabel.setText("Incorrect!");
		                                    g.changeTurn();
		                                }
		                                refresh();
		                            }
		                            refresh();
		                        }
		                    }
		                }
		    refresh();
		    }
		    catch(IOException ex){
		        
		    }
	    }
    } else {
    	toDisplayLabel.setText("Please press \"new game\"");
    }
}


}
