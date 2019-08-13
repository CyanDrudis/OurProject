package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXMLDocumentController {
	/*******************************************************************************************************************
	 * 
	 * CLASS: FXMLDocumentController
	 * 
	 * PURPOSE: This class is used to handle the events generated from interacting with buttons, text fields, 
	 * loading, saving, and some general game logic.
	 * 
	 * PRIVATE VARIABLES: 	-degreesPerSpoke : int , used for the calculation of spinning the wheel.
	 * 						-spinInput : boolean , used to make sure the user can only input 1 spin at a time.
	 * 						-gameInitialized : boolean , makes sure a new game is created or one is loaded before someone attempts to play.
	 * 						-oldSpokeIndex : int , used for the calculation of spinning the wheel.
	 * 						-viewList : ObvservableList<String> , used for the view list for what is happening in the game.
	 * (loads of private labels, buttons, fonts, and few text fields and drop down menus auto generated from Gluon javafx scene builder)
	 * 
	 * SOURCES: 
	 * http://www.java2s.com/Code/Java/JavaFX/Menuitemeventhandler.htm
	 * https://stackoverflow.com/questions/39163881/javafx-drop-down-button
	 * https://docs.oracle.com/javafx/2/api/javafx/animation/RotateTransition.html
	 * https://examples.javacodegeeks.com/desktop-java/javafx/listview-javafx/javafx-listview-example/
	 * https://www.youtube.com/watch?v=2PxU7q9xl38
	 * https://www.youtube.com/watch?v=ZuHcl5MmRck
	 * https://www.youtube.com/watch?v=pzQXd08BPy4
	 * https://www.youtube.com/watch?v=fl6Wp1I9wHQ
	 *******************************************************************************************************************/

	Game g = new Game();
	String spoke = "";
    private final int degreesPerSpoke = 15;
    private boolean spinInput = false;
    private boolean gameInitialized = false;
    private int oldSpokeIndex = 0;
    private int oldPlayersTurn = -1;
    ObservableList<String> viewList = FXCollections.<String>observableArrayList();//source: https://examples.javacodegeeks.com/desktop-java/javafx/listview-javafx/javafx-listview-example/

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
    private ImageView wheelImageView;
    
    @FXML
    private Button newGameButton;
    
    @FXML
    private Label player1Total;

    @FXML
    private Label player2Total;

    @FXML
    private Label player3Total;
    
    @FXML
    private Button loadGameButton;

    @FXML
    private Button saveGameButton;

    @FXML
    private MenuButton LoadGamePath;
    
    @FXML
    private TextField saveGamePath;
    
    @FXML
    private Button twoPlayerButton;

    @FXML
    private Button threePlayerButton;
    
    @FXML
    private ListView<String> toDisplayLabel;
    
    @FXML
    private MenuItem player1Name;

    @FXML
    private MenuItem player2Name;

    @FXML
    private MenuItem player3Name;

    @FXML
    private Button importButton;
    
    @FXML
    private TextField playerNameField;
    /**
	 * 	 
	 * Used to set the player 1 name.
	 * (activated by player1Name menu item)
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void player1NameAction(ActionEvent event) {
    	player1Label.setText(playerNameField.getText());
    }
    /**
	 * 	 
	 * Used to set the player 2 name.
	 * (activated by player2Name menu item).
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void player2NameAction(ActionEvent event) {
    	player2Label.setText(playerNameField.getText());
    }
    /**
	 * 	 
	 *  Used to set the player 3 name.
	 * (activated by player3Name menu item).
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void player3NameAction(ActionEvent event) {
    	player3Label.setText(playerNameField.getText());
    }
    /**
	 * 	 
	 * This method is used to import the names of the game saves from the src/gamesaves folder.
	 * (activated by "import" button). The method uses a for loop with the terminating condition 
	 * being that the index is less than the amount of game saves. The previous save is found by
	 * passing the save name in LoadGamePath and the game is then adjusted accordingly. 
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void loadPreviousSaves(ActionEvent event) {
    	//source: https://stackoverflow.com/questions/39163881/javafx-drop-down-button
    	LoadGamePath.getItems().clear();
    	for(int i = 0; i < g.getGameSaveNames().size(); i++) {
    		String saveName = g.getGameSaveNames().get(i);
    		LoadGamePath.getItems().add(new MenuItem(saveName));
    		LoadGamePath.getItems().get(i).setOnAction(changeTabPlacement());
    	}
    }
    /**
	 * 	 
	 * This method is used to set the menu button's text to the text selected in the menu.
	 * (activated by clicking a menu item). 
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    private EventHandler<ActionEvent> changeTabPlacement() {
    	//source: http://www.java2s.com/Code/Java/JavaFX/Menuitemeventhandler.htm
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MenuItem mItem = (MenuItem) event.getSource();
                String side = mItem.getText();
                LoadGamePath.setText(side);
            }
        };
    }
    /**
	 * 	 
	 * This method is used to set the game to three player.
	 * (activated by clicking three player button). Implementation
	 * is simple, the method activates all functionality related to
	 * player three such as buttons and labels. 
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void threePlayerMode(ActionEvent event) {
    	g.setTwoPlayerMode(false);
    	twoPlayerButton.setDisable(false);
		threePlayerButton.setDisable(true);
		player3Total.setVisible(true);
		player3MoneyLabel.setVisible(true);
		player3Label.setVisible(true);
		refresh();
    }
    /**
	 * 	 
	 * This method is used to set the game to two player.
	 * (activated by clicking two player button). This method 
	 * implementation is simple, the third player buttons and labels
	 * are disabled. It also calls who'sTurn from Game class to reset 
	 * the turn.
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void twoPlayerMode(ActionEvent event) {
    	g.setTwoPlayerMode(true);
    	player3Total.setVisible(false);
		player3MoneyLabel.setVisible(false);
		player3Label.setVisible(false);
		twoPlayerButton.setDisable(true);
		threePlayerButton.setDisable(false);
		if(g.whosTurn() == 2) {
			g.setTurn(0);
		}
		refresh();
    }
    /**
	 * 	 
	 * This method is used to load a game, check to see if it is 2 or 3 player then refresh the board.
	 * (activated by clicking load game button). This method loads the game, and imports the previous wheel
	 * and inputed string. It will also check if the previous game was in two player mode or three player
	 * mode and disable any buttons or labels accordingly. 
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 * @throws IOException
	 */
    @FXML
    void loadGame(ActionEvent event) throws IOException {
    	if(!LoadGamePath.getText().isEmpty()) {
    		g.loadGame(LoadGamePath.getText());
    		g.importWheel();      
			g.setCurrent();
			twoPlayerButton.setVisible(true);
			threePlayerButton.setVisible(true);
			if(g.getTwoPlayerMode()) {
				player3Total.setVisible(false);
				player3MoneyLabel.setVisible(false);
				player3Label.setVisible(false);
				twoPlayerButton.setDisable(true);
				threePlayerButton.setDisable(false);
			} else {
				twoPlayerButton.setDisable(false);
				threePlayerButton.setDisable(true);
				player3Total.setVisible(true);
				player3MoneyLabel.setVisible(true);
				player3Label.setVisible(true);
			}
			gameInitialized = true;
    		refresh();
    	}
    }
    /**
	 * 	 
	 * This method is used to save the current game state to src/gamesaves/
	 * (activated by clicking save game button). Method ensures that if the
	 * text field for saving a game in empty that it will always be available.
	 * Otherwise it will be called Default.
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 */
    @FXML
    void saveGame(ActionEvent event) {
    	if(!saveGamePath.getText().isEmpty()){
    		g.saveGame(saveGamePath.getText());
    	} else {
    		g.saveGame("DEFAULT");
    	}
    }
    
    /**
   	 * 	 
   	 * This method is used to initialize the game.
   	 * (activated by clicking new game or loading a previous save).
   	 * Implementation of this function is simple, it just calls the 
     * game initializer function gameInit().
   	 * 
   	 * @param ActionEvent event
   	 * 
   	 * @returns none
   	 * 
   	 */
    @FXML
    void generateNewGame(ActionEvent event) {
			gameInit();
    }
   
    /**
   	 * 	 
   	 * This method is what generateNewGame(ActionEvent event) calls to initialize a new game.
   	 * (activated by clicking new game). Various method from the Game class are called here.
   	 * A new game is made, the wheel is imported and the player 1's turn is set.
   	 * It also makes the players buttons visible.
   	 * 
   	 * Try-catch block is there for IOexceptions.
   	 * 
   	 * @param ActionEvent
   	 * 
   	 * @returns none
   	 * 
   	 */
       
    @FXML
    public void gameInit() {
        try {
			g.newGame();
			g.importWheel();      
			g.setCurrent();
			g.setTurn(0);
			twoPlayerButton.setVisible(true);
			threePlayerButton.setVisible(true);
			threePlayerButton.setDisable(true);
			refresh();
			gameInitialized = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
	 * 	 
	 * This method is used to make the wheel spin.
	 * (activated by display(ActionEvent event)). The 
	 * method first retrieves the wheel array and sets an 
	 * animation for the wheel.png to rotate in an effort 
	 * to simulate a wheel spin. Taking a random index from
	 * the array is equivalent to the spoke landing on a random
	 * value on the wheel. Note, that the spin button is 
	 * disabled while the wheel animation is ongoing, so
	 * that the wheel spin is not reset if the user decided
	 * to click on it consecutively. 
	 * 
	 * The purpose of the try-catch block is just in case
	 * there is any error in finding the relevant files needed
	 * for the wheel.
	 * 
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * @throws InterruptedException
	 */
    @FXML
	public void spinWheel() throws InterruptedException{
    	//sources: 
    	//https://docs.oracle.com/javafx/2/api/javafx/animation/RotateTransition.html
    	//
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
    /**
	 * 	 
	 * This method is used to refresh the entire screen every time something of significance happens.
	 * (activated by multiple processes). The method retrieves information about the game such as
	 * balance or who's turn it is. If the turns aren't compatible, the old players turn is not their
	 * turn, it is set to the correct active player.
	 * 
	 * Try-catch block is used to catch IO and Null Pointer exceptions. 
	 * @param none
	 * 
	 * @returns none
	 * 
	 */
	@FXML
	public void refresh() {
		try {
			g.setCurrent();
			char check[] = g.getCurrent().toCharArray();
			String puzzleText = "";
			for(int i = 0; i < check.length; i++) {
				if(check[i]=='*') {
					puzzleText += "-";
				} else {
					puzzleText += check[i];
				}
			}
			puzzleLabel.setText("Puzzle: " + puzzleText);
			player1MoneyLabel.setText("$"+g.money[0]);
			player2MoneyLabel.setText("$"+g.money[1]);
			player3MoneyLabel.setText("$"+g.money[2]);
			player1Total.setText("Total: $" + g.players.get(0).getMoney());
			player2Total.setText("Total: $" + g.players.get(1).getMoney());
			player3Total.setText("Total: $" + g.players.get(2).getMoney());
			int playersTurn = g.whosTurn()+1;
			if(oldPlayersTurn != playersTurn) {
				viewList.add("Player " + playersTurn + "'s turn.");
				oldPlayersTurn = playersTurn;
			}
			toDisplayLabel.getItems().clear();
			toDisplayLabel.getItems().addAll(viewList);
			if(viewList.size()>0) {
				toDisplayLabel.scrollTo(viewList.size());
			}
			categoryLabel.setText("Category : " + g.getPuzzle());
		} catch (IOException e) {
				System.out.println("IOException, check file location");
			e.printStackTrace();
		} 
		catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * 	 
	 * This method is the game logic that runs every turn.
	 * (activated by clicking spin button). The method first 
	 * checks if any player has won the game and prompts 
	 * the user as to which player has won. Otherwise the 
	 * the game continues and then goes on to check whether
	 * or not the player has solved a puzzle, first ensuring
	 * the answer text box is not empty. Again, if none of 
	 * these are true, the game then allows the player to 
	 * take a spin of the wheel and handles any of the 
	 * special non digit spoke values like loseaturn or freespin. 
	 * Finally, if none of the above is true, the game 
	 * will prompt the player to start a new game.
	 * 
	 * @param ActionEvent event
	 * 
	 * @returns none
	 * 
	 * @throws IOException, InterruptedException
	 * 
	 */
    @FXML
    public void display(ActionEvent event) throws InterruptedException, IOException {
    	String input = textField.getText();
    	char check[] = input.toCharArray();
    	if(g.win()) {
    		int playersTurn = g.getHighestBalPlayer() + 1;
    		viewList.add("Player " + playersTurn + " won with a balance of: " + g.players.get(g.getHighestBalPlayer()).getMoney());
    		spinClick.setDisable(true);
    	}else if(gameInitialized) {
	    	if(textField.getText().equals("")) {
	    		viewList.add("Please enter in a letter or guess the puzzle");
		    }else{
		    	if(check.length>1) {
			    	if(g.checkAns(input)){	
	                	int playersTurn = g.whosTurn() + 1;
	                	viewList.add("Player " + playersTurn + " solved it!, New Puzzle!");
	                    g.winPuzzleCounter();
	                    g.newPuzzle();
	                    g.setCurrent();
	                }else{
	                	viewList.add("Incorrect!");
	                    g.changeTurn();
	                }
		    	} else {
		    		spinWheel();
		    		try{
		    			spinInput = true;
		    			if(spinInput){
		    	spinInput = false;
		                    while(spoke.equals("freespin")){
		                        spinWheel();
		                    }    
		                    int playersTurn = g.whosTurn() + 1;
		                    if (spoke.equals("loseaturn")) {
		                        g.loseATurn();
		                        viewList.add("Sorry you lose a turn!");
		                        playersTurn = g.whosTurn() + 1;
		                    }else{
		                        if(spoke.equals("bankrupt")){
		                            g.bankrupt();
		                            viewList.add("You went bankrupt!");
		                        }else{
		                            if(check.length == 1){
		                                char a = check[0];
		                                String outcome = g.inputChar(a);
		                                if(outcome.equals("there")) {
		                                	viewList.add("Correct");
		                                } else if (outcome.equals("notThere")){
		                                	viewList.add("Incorrect");
		                                    g.changeTurn();
		                                } else if (outcome.equals("alreadyThere")) {
		                                	viewList.add("Already guessed");
		                                    g.changeTurn();
		                                } else if (outcome.equals("puzzleComplete")){
		                                	g.winPuzzleCounter();
		                                    g.newPuzzle();
		                                    g.setCurrent();
		                                    playersTurn = g.whosTurn() + 1;
		                                    viewList.add("Player " + playersTurn + " solved it!, New Puzzle!");
					                    		}
					                            }
					                        }
					                    }
					                }
					    }
					    catch(IOException ex){
					    	ex.printStackTrace();
					    }
		    		} 
		    	}
    	} else {
    		viewList.add("Please press \"new game\"");
    	}
    	refresh();
    }
}
