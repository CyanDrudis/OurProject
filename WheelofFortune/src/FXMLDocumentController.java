package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class FXMLDocumentController {
	Game g = new Game();
	String spoke = "";
	private boolean startGame = false;
    private final int wheelDimensions = 420;
    private final int halfWayDimensions = 210;
    private final int degreesPerSpoke = 15;
    private boolean spinInput = false;
    private boolean gameInitialized = false;
    private void test() {
    	System.out.println("test");
    }
    @FXML
    private Label player1Label;

    @FXML
    private Font x3;

    @FXML
    private Label player1MoneyLabel;

    @FXML
    private Button spinButton;

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
    void generateNewGame(ActionEvent event) {
			gameInit();
    }
    @FXML
    public void gameInit() {
    	
        try {
			g.newGame();
			g.importWheel();      
			g.setCurrent();
			g.winPuzzleCounter();
			refresh();
			gameInitialized = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
	public void spinWheel(){
		try{
		  spoke = g.spin();
		  }
		  catch(IOException ex){
			  System.out.println("IOException, check file location");
		      ex.printStackTrace();
		  }
		  int spokesToTurn = g.getRandomNumForWheel();
		  double rotationRequired = degreesPerSpoke*spokesToTurn;
		  wheelImageView.setRotate(rotationRequired); 
		  spinInput = true;
	}
	
	@FXML
	public void refresh() {
		try {
			g.setCurrent();
			puzzleLabel.setText("Puzzle: " + g.getCurrent());
			player1MoneyLabel.setText("$"+g.players.get(0).getMoney());
			player2MoneyLabel.setText("$"+g.players.get(1).getMoney());
			player3MoneyLabel.setText("$"+g.players.get(2).getMoney());
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
    public void display(ActionEvent event) {
    	if(gameInitialized) {
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
		                                    g.newGame();
		                                    g.importWheel();
		                                    g.setCurrent();
		                                    playersTurn = g.whosTurn() + 1;
		                                    toDisplayLabel.setText("Player " + playersTurn + " solved it!, New Puzzle!");
		                                    refresh();
		                                }
		                                refresh();
		                            }
		                            else{
		                                if(g.checkAns(input)){
		                                    g.winPuzzleCounter();
		                                    g.newGame();
		                                    g.importWheel();
		                                    g.setCurrent();
		                                    playersTurn = g.whosTurn() + 1;
		                                    toDisplayLabel.setText("Player " + playersTurn + "solved it!, New Puzzle!");
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
