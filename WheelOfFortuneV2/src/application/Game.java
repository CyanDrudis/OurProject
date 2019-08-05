package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/*********************************************************************************************************************
 * CLASS: Game
 * 
 * PURPOSE: 
 *
 *			In addition to the methods of the game, there are methods to pass, set and modify relevant information about 
 *			the player as the game goes on. This is represented as a players 'account' (the amount of money they have),
 * 			their name and the prize list attached to them.
 *
 *
 * 
 * PRIVATE VARIABLES: -puzzle: a String, beginning with asterisks (*) that shows the length of the word that the player
 *							will have to guess, and will fill up gradually as they guess characters
 *					  -answer: a String, the word that the players will be guessing
 *					  -whosTurn: keeps track of who's turn it is, used to skip turns as well			
 *					  -ArrayList<String> wheel = array list of the wheel which has all the available "prizes"
 *					  -ArrayList<String> current = array list of the current  
 *					  -ArrayList<String> guessed = array list of guessed letters
 *					  -answers: a character array which takes the answer and separates it into characters
 *					  -numberOfPuzzles: keeps track of number of puzzles completed	
 *
 *					  -name: a String, the player's name
 *                    -money: a double, the player's 'account' so to say  
 *                    -bonus:   
 *                    -vowelCost:
 *                    -prizeList: 
 *                    
 * NOTES: Negative balance is possible and allowed in Wheel of Fortune, therefore it is not accounted for here.
 *********************************************************************************************************************/

public class Game {
	private String puzzle;
	private int currentSpokeValue;
	private String answer;
	private int whosTurn = 0; //0 = player, 1 = player 2, 2 = player 3
	private static ArrayList<String> current = new ArrayList<String>();
	private static ArrayList<String> guessed = new ArrayList<String>();
	private char[] answers;
	private final double prizeIfBankrupt = 1.0;
	private static final double vowelCost = 50;
	private static final int numberOfPuzzlesToWin = 3;
	private int numberOfPuzzles = 0;
    private int randomNumForWheel;
	Puzzle p = new Puzzle();
	ArrayList<String> wheel = new ArrayList<String>();
	ArrayList<Player> players = new ArrayList<Player>();
	double[] money = {0.0, 0.0, 0.0};
	
	/**
	 * 
	 * This method is used to deposit an amount in the players 
	 * temporary account, which player receives the money depends 
	 * on who's turn it is, signified by the whosTurn instance variable. 
	 * This temporary account's balance will then be transferred
	 * over to a permanent account that will keep the total balance 
	 * which will be used to substatiate the winner of the game.
	 * 
	 * @param amount the amount of money that will be deposited into the 
	 * players account
	 * 
	 * @return void
	 * 
	 * 
	 */
	
	public void deposit(double amount) {
		money[whosTurn] += amount;
	}
	
	/**
	 * 
	 * This method is used to withdraw an amount from the players temporary 
	 * account, which player has the money withdrawn depends on who's turn 
	 * it is, signified by the whosTurn instance variable. This temporary 
	 * account's balance will then be transferred over to a permanent 
	 * account that will keep the total balance which will be used to
	 * substatiate the winner of the game.
	 * 
	 * @param amount the amount of money that will be withdrawn from the 
	 * players account
	 * 
	 * @return void
	 * 
	 * 
	 */
	
	public void withdraw(double amount) {
		money[whosTurn] -= amount;
	}
	
	/**
	 * 
	 * This method is used to get a random number for the wheel, this is 
	 * used to ensure that there is no specific order of the values that 
	 * the wheel can return. The values that the wheels returns from is in 
	 * a text file called wheel.txt. 
	 * 
	 * @param none
	 * 
	 * @returns integer that's used as an index to get a certain value of 
	 * the wheel from the text
	 * file. 
	 * 
	 * 
	 */
	
    public int getRandomNumForWheel(){
        return randomNumForWheel;
    }
    
	/**
	 * 	 
	 * This method is used to import the wheel, it calls on a method that's
	 * found in the puzzle class, which takes all of the values found in 
	 * the wheel.txt file and imports them all into an array. 
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
    
	public void importWheel() throws IOException {
		wheel = p.getWheel();
	}
	
	/**
	 * 
	 * This method returns the puzzle that represents the category of puzzle
	 * that the user will be guessing from, found from the puzzle text file
	 * 
	 * @param none
	 * 
	 * @returns a puzzle of type String that represents the category of puzzle
	 * that the user will be getting the String that they will have to guess
	 * 
	 * 
	 */
	
	public String getPuzzle() {
		return puzzle;
	}
	
	/**
	 * 
	 * This method retrieves the balance of the player who's turn it currently
	 * is on, it calls on the getMoney function that's implemented in the same
	 * Game class. 
	 *  
	 * @param none
	 * 
	 * @returns the balance of the player who's turn it current is
	 * 
	 * 
	 */
	
	public double getBal() {
		return players.get(whosTurn).getMoney();
	}
	
	/**
	 * 
	 * This method sets the balance of a player, it can set the balance
	 * of any player regardless of return and assigns a balance to it
	 *
	 * @param whosTurn an integer which indicates whos turn it is in order
	 * to set the balance for the correct player
	 * 
	 * @param money the amount of money that will be set for that specific
	 * player
	 * 
	 * @returns void
	 * 
	 * 
	 */
	
	public void setBal(int whosTurn, int money) {
		players.get(whosTurn).setMoney(money);
	}
	
	/***************************************************************************************
	 * FUNCTION: Getting highest balance
	 * 
	 * DESCRIPTION: 
	 * 
	 * 
	 * METHOD: getHighestBalPlayer()
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:
	 * 
	 *********************************************************************************************/
	public int getHighestBalPlayer() {
		int highest = 0;
		double highestBal = 0.0;
		for(int i = 0; i <= 2; i++) {
			if(players.get(i).getMoney() > highestBal) {
				highestBal = players.get(i).getMoney();
				highest = i;
			}
		}
		return highest;
	}
	
	/***************************************************************************************
	 * FUNCTION: Getting player list size
	 * 
	 * DESCRIPTION: 
	 * 
	 * 
	 * METHOD: getPlayerListSize()
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:
	 * 
	 *********************************************************************************************/
	public int getPlayerListSize() {
		return players.size();
	}
	
	/***************************************************************************************
	 * FUNCTION: creating a brand new game (puzzle and answer)
	 * 
	 * DESCRIPTION: creates a brand new game for the player to try and win, chooses a puzzle,
	 * then chooses an answer from that puzzle category, creates three player accounts
	 * 
	 * METHOD: newGame()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void  
	 * 
	 *********************************************************************************************/
	public void newGame() throws IOException {
		p = new Puzzle();
		guessed.clear();
		puzzle = p.getPuzzle();
		answer = p.getAnswer();
		answers = answer.toCharArray();
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
	}
	
	/***************************************************************************************
	 * FUNCTION: Create a new puzzle and obtain an answer from that puzzle
	 * 
	 * DESCRIPTION: transfer answer to character array, create new answer and puzzle
	 * 
	 * METHOD: newPuzzle()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void
	 * 
	 *********************************************************************************************/
	public void newPuzzle() throws IOException {
		p = new Puzzle();
		puzzle = p.getPuzzle();
		answer = p.getAnswer();
		answers = answer.toCharArray();
		players.get(0).deposit(money[0]);
	}
	
	/***************************************************************************************
	 * FUNCTION: Reset users money upon landing on bankrupt section of the wheel
	 * 
	 * DESCRIPTION: sets player who lands on the bankrupt section's money to 0
	 * 
	 * METHOD: bankrupt()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void
	 * 
	 *********************************************************************************************/
	public void bankrupt() {
		money[whosTurn] = 0;
        changeTurn();
	}
	
	/***************************************************************************************
	 * FUNCTION: Cycle through players turn	
	 * 
	 * DESCRIPTION: Cycle through players turn 	
	 * 
	 * METHOD: loseATurn()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void
	 * 
	 *********************************************************************************************/
	public void loseATurn(){
		if(whosTurn == 2) {
			whosTurn = 0;
		}	else	{
			whosTurn += 1;
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: Set Turn
	 * 
	 * DESCRIPTION: 
	 * 
	 * METHOD: setTurn()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void
	 * 
	 *********************************************************************************************/
	public void setTurn(int turn){
		whosTurn = turn;
	}
	
	/***************************************************************************************
	 * FUNCTION: Set the base string to which the user is going to guess characters to fill up the word 
	 * 
	 * DESCRIPTION: Go through each index of the length of the answer, and adds either blank spaces or
	 * asterisks 
	 * 
	 * METHOD: setCurent()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void  
	 * 
	 *********************************************************************************************/
	public void setCurrent() throws IOException {
		char[] check;
		check = answer.toCharArray();
                current.clear();
		for(int i = 0; i < check.length; i++) {
			if(check[i] == ' ') {
                            current.add(" ");	
			} else  if(containsCaseInsensitive(check[i]+"", guessed)){
                            current.add(check[i]+"");
			} else {
                            current.add("*");
			}
		}
	}
	
        /***************************************************************************************
	 * FUNCTION: 
	 * 
	 * DESCRIPTION: 
	 * 
	 * METHOD: containsCaseInsensitive(String strToCompare, ArrayList<String>list)
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:
	 * 
     * SOURCE: https://stackoverflow.com/questions/8751455/arraylist-contains-case-sensitivity
	 *********************************************************************************************/
	public boolean containsCaseInsensitive(String strToCompare, ArrayList<String>list) {
         for(String str:list){
              if(str.equalsIgnoreCase(strToCompare)){
                  return(true);
              }
         }
     return(false);
     }
	
	/***************************************************************************************
	 * FUNCTION: Returns the current string
	 * 
	 * DESCRIPTION: Returns the current string by converting the string array to string
	 * 
	 * METHOD: getCurrent
	 * 
	 * RETURNS: String of the already guessed word
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
        
	public String getCurrent() throws IOException {
		String toReturn = "";
		for(int i = 0; i < current.size(); i ++) {
			toReturn += current.get(i);
		}
		return toReturn;
	}
	
	/***************************************************************************************
	 * FUNCTION: Returns the guessed string
	 * 
	 * DESCRIPTION: Returns the current string by converting the string array to string
	 * 
	 * METHOD: getGuessed
	 * 
	 * RETURNS: String of the already guessed word
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public String getGuessed() throws IOException {
		String toReturn = "";
		for(int i = 0; i < guessed.size(); i ++) {
			toReturn += guessed.get(i);
		}
		return toReturn;
	}
	
	
	/***************************************************************************************
	 * FUNCTION: gets a random index of the wheel
	 * 
	 * DESCRIPTION: gets a random index of the wheel, which is the 'prize' that the user will be
	 * gaining money from if they guess characters correctly, or if it's a special type of prize
	 * such as bankrupt/1/2car, they will be receiving that 
	 * 
	 * METHOD: spin()
	 * 
	 * RETURNS: String
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public String spin() throws IOException {
		Random rand = new Random();
                if(wheel.size()>0){
                randomNumForWheel = rand.nextInt(wheel.size());
                }
                setCurrent();
		if(!wheel.get(randomNumForWheel).equals("bankrupt")&& !wheel.get(randomNumForWheel).equals("loseaturn") && !wheel.get(randomNumForWheel).equals("freespin")) {
			currentSpokeValue = Integer.valueOf(wheel.get(randomNumForWheel));
		}
		return wheel.get(randomNumForWheel); //get a random index from the arraylist, may have to change later
	}
	
	/***************************************************************************************
	 * FUNCTION: checks string guessed to answer
	 * 
	 * DESCRIPTION: when the user is ready to guess the entire word, they will input an answer 
	 * of type string which will be compared to the actual answer
	 * 
	 * METHOD: checkAns()
	 * 
	 * RETURNS: Boolean
	 *
	 * INPUT PARAMETERS: String
	 * 
	 *********************************************************************************************/
	public boolean checkAns(String a) throws IOException {
		if(a.toLowerCase().trim().equalsIgnoreCase(answer.toLowerCase().trim())) {
            guessed.clear();
            current.clear();
			return true;
		}
		return false;
	}
	
	/***************************************************************************************
	 * FUNCTION: checks string to return guessed answer list
	 * 
	 * DESCRIPTION: allows user to check what letters they have guessed
	 * 
	 * METHOD: checkGuess()
	 * 
	 * RETURNS: Boolean
	 *
	 * INPUT PARAMETERS: String
	 * 
	 *********************************************************************************************/
	public boolean checkGuess(String a) throws IOException {
		if(a.equalsIgnoreCase("check guessed")){
			return true;
		}
		return false;
	}
	
	/***************************************************************************************
	 * FUNCTION: Changes who's turn it is
	 * 
	 * DESCRIPTION: Changes who's turn it is
	 * 
	 * METHOD: changeTurn()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public void changeTurn(){
		if(whosTurn == 2) {
			whosTurn = 0;
		}	else	{
			whosTurn += 1;
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: Lets user know who's turn it is
	 * 
	 * DESCRIPTION: Returns which user's turn it is
	 * 
	 * METHOD: whosTurn();
	 * 
	 * RETURNS: Int
	 *
	 * INPUT PARAMETERS: none 
	 * 
	 *********************************************************************************************/
	public int whosTurn()	{
		return whosTurn;
	}
	/***************************************************************************************
	 * FUNCTION: checks inputted character with the guessed array and answer
	 * 
	 * DESCRIPTION: first compared inputted character to the guessed array to see if it's already
	 * been guessed, then is compared it to the answer, and added to the guessed array, or if it
	 * 
	 * METHOD: inputChar()
	 * 
	 * RETURNS: Boolean
	 *
	 * INPUT PARAMETERS:  char
	 * 
	 *********************************************************************************************/
	public String inputChar(char a) throws IOException {
		
		for (int i=0;i<guessed.size();i++){
			if ((a+"").equals(guessed.get(i))) {
				return "alreadyThere";
			} 
		}
        boolean there = false;
		for (int i = 0; i < answers.length; i++) {
			if ((a+"").equalsIgnoreCase(answers[i]+"")) {
                            there = true;
				guessed.add(a+"");
				if(a == 'a' ||a == 'e' ||a == 'i' ||a == 'o' || a =='u'){
					money[whosTurn] -= vowelCost;
				}
				else{
					money[whosTurn] += currentSpokeValue;
				}
                
			}
		}
		setCurrent();
		if(!current.contains("*")){
			guessed.clear(); 
			return "puzzleComplete";
		}
        if(there){
        	return "there";
        }
        
		return "notThere";
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * DESCRIPTION: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: none	
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public void setWinPuzzleCounter(int zero) {
		numberOfPuzzles = zero;
	}
	
	/***************************************************************************************
	 * FUNCTION: Increasing the puzzle counter
	 * 
	 * DESCRIPTION: Increasing the puzzle counter to see when the win condition has been reached
	 * 
	 * METHOD: win()
	 * 
	 * RETURNS: none	
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public void winPuzzleCounter() {
		numberOfPuzzles = numberOfPuzzles + 1;
		for(int i = 0; i <= 2; i++) {
			if(money[i] >= 0.0) {
			players.get(i).deposit(money[i]);
			}
			else {
				players.get(i).deposit(prizeIfBankrupt);
			}
			money[i] = 0.0;
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: The win scenario
	 * 
	 * DESCRIPTION: when the number of puzzles to win has been reached, the method will return
	 * true 
	 * 
	 * METHOD: win()
	 * 
	 * RETURNS: Boolean		
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public boolean win() {	
		if (numberOfPuzzles >= numberOfPuzzlesToWin) {
			return true;
		}
		return false;
	}
	/***************************************************************************************
	 * FUNCTION: The win scenario
	 * 
	 * DESCRIPTION: when the number of puzzles to win has been reached, the method will return
	 * true 
	 * 
	 * METHOD: win()
	 * 
	 * RETURNS: Boolean		
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public boolean winPuzzle() {
		for(int i = 0; i <current.size(); i ++) {
			if(current.get(i) == "*") {
				return false;
			}
		}
                guessed.clear();
		return true;         
	}
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * DESCRIPTION: 
	 * 
	 * METHOD: saveGame(String saveName)
	 * 
	 * RETURNS:		
	 *
	 * INPUT PARAMETERS: saveName : String
	 * 
	 *********************************************************************************************/
	public void saveGame(String saveName) {
		BufferedWriter output = null;
		try {
            File file = new File("src/application/" + saveName + ".txt");
	        output = new BufferedWriter(new FileWriter(file));
	        output.write(money[0]+";"+money[1]+";"+ money[2]+"\n");
	        output.write(players.get(0).getMoney() + ";"+ players.get(1).getMoney() + ";" +players.get(2).getMoney()+"\n");
	        for(int i = 0; i < guessed.size(); i++) {
	        	output.write(guessed.get(i));
	        }
	        output.write("\n");
	        output.write(answer);
	        output.write("\n");
	        output.write(getPuzzle());
	        output.write("\n");
	        output.write(whosTurn+"\n");
	        output.write(getRandomNumForWheel()+"\n");
	        output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
	}
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * DESCRIPTION: 
	 * 
	 * METHOD: saveGame(String saveName)
	 * 
	 * RETURNS:		
	 *
	 * INPUT PARAMETERS: saveName : String
	 * 
	 *********************************************************************************************/
	public void loadGame(String saveName) {
		try {
            File file = new File("src/application/" + saveName + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
			// used (above line)code from: https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
			String line;
			line = br.readLine();
			String[] check= line.split(";");
			money[0] = Double.valueOf(check[0]);
			money[1] = Double.valueOf(check[1]);
			money[2] = Double.valueOf(check[2]);
			line = br.readLine();
			check = line.split(";");
			players.add(new Player());
			players.add(new Player());
			players.add(new Player());
			players.get(0).setMoney(Double.valueOf(check[0]));
			players.get(1).setMoney(Double.valueOf(check[1]));
			players.get(2).setMoney(Double.valueOf(check[2]));
			line = br.readLine();
			char[] checkChar = line.toCharArray();
			for(int i = 0; i < checkChar.length; i++) {
				guessed.add(checkChar[i]+"");
			}
			line = br.readLine();
			answer = line;
			answers = answer.toCharArray();
			line = br.readLine();
			puzzle = line;
			line = br.readLine();
			whosTurn = Integer.valueOf(line.trim());
			line = br.readLine();
			randomNumForWheel = Integer.valueOf(line.trim());
			br.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
	}
}