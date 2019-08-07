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
    private boolean twoPlayerMode = false;
	Puzzle p = new Puzzle();
	ArrayList<String> wheel = new ArrayList<String>();
	ArrayList<Player> players = new ArrayList<Player>();
	double[] money = {0.0, 0.0, 0.0};
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public void setTwoPlayerMode(boolean bool) {
		twoPlayerMode = bool;
	}
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public boolean getTwoPlayerMode() {
		return twoPlayerMode;
	}
	
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
	
    /** 
     *
     * This method sets the balance of a player, it can set the balance
     * of any player regardless of return and assigns a balance to it
     *
     * @param none
     * 
     * @returns void
     * 
     * 
     */ 
	
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
	
   /** 
     *
     * This retrieves the player list size stored in the PlayerList array,
     * this is useful so when the logic is implemented to allow a set amount
     * of players to play in the game, and when the game resets, a loop
     * will be performed where the players balance is set to 0, therefore,
     * needing the player list size to loop through all the indices
     *
     * @param none
     * 
     * @returns an integer that represents the size of the PlayerList array
     * 
     * 
     */ 
	
	public int getPlayerListSize() {
		return players.size();
	}
	
   /** 
	 *
  	 * This method creates a brand new game first by creating a new Puzzle(),
  	 * which is the shell of the game, the guessed array is then cleared, 
  	 * a new puzzle category is retrieved using the getPuzzle() method, then
  	 * the answer is retrieved using the getAnswer() method, according to the 
  	 * category. The string answer is then converted into a character array 
  	 * which will then be used to compare the guesses towards. The default 
  	 * amount of players is three, therefore, three instances of player are
  	 * created, and added to an array list for players
  	 * 
  	 * @param void
  	 * 
  	 * @returns void
  	 * 
  	 * @throws If an input or output exception occurred
  	 * 
  	 * 
  	 */ 
	
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
	
	/**
	 * 	 
	 * This method creates a new puzzle by retrieving the category from the
	 * puzzle text file, as well, a corresponding answer will be picked from
	 * the category. This puzzle is then transferred to a character array,
	 * and every player gets their balance reset to 0.
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public void newPuzzle() throws IOException {
		p = new Puzzle();
		puzzle = p.getPuzzle();
		answer = p.getAnswer();
		answers = answer.toCharArray();
		players.get(0).deposit(money[0]);
	}
	
	/**
	 * 	 
	 * This method resets users money upon landing on bankrupt section of
	 * the wheel, by setting the player's balance to 0. The player who's
	 * balance will be reset will depedn on the whosTurn instance variable,
	 * as they are the person who landed on it 
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void bankrupt() {
		money[whosTurn] = 0;
        changeTurn();
	}
	
	/**
	 * 	 
	 * This method is used to increment a players turn as the player finishes
	 * their turn, or in the scenario where they land on the loseaturn spoke
	 * on the wheel, in which case, they lose their chance to guess any letters,
	 * and their turn gets skipped, and passed onto the next person
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void loseATurn(){
		if(twoPlayerMode) {
			if(whosTurn == 1) {
				whosTurn = 0;
			} else {
				whosTurn = 1;
			}
		} else if( whosTurn == 2) {
			whosTurn = 0;
		}	else	{
			whosTurn += 1;
		}
	}
	
	/**
	 * 	 
	 * This method sets the turn, and is used when...
	 * 
	 * 
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void setTurn(int turn){
		whosTurn = turn;
	}
	
	/**
	 * 	 
	 * This method creates an empty array called check, that is then set to
	 * a character array of answer, the current array is then cleared for 
	 * the updated current character array. For each index of the answer, if
	 * the check is equal to a space, there's a space added in the current 
	 * array, if it's equivalent to any of the guessed letters regardless
	 * of lower/uppercase guess, it's reaplaced in the current string, and
	 * if not, the un-guessed letters have asterisks inserted
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public void setCurrent() throws IOException {
		char[] check;
		check = answer.toCharArray();
        	current.clear();
		for(int i = 0; i < check.length; i++) {
			if(check[i] == ' ') {
				current.add(" ");	
			} else  if (containsCaseInsensitive(check[i]+"", guessed)){
				current.add(check[i]+"");
			} else {
                current.add("*");
			}
		}
	}
	
	/**
	 * 	 
	 * This method is used when the user inputs more than one character,
	 * it assumes that what the user has put is the entire answer, 
	 * this guess will then be compared to the answer, and will return
	 * a boolean value
	 * 
	 * @param strToCompare the String that's being compared
	 * 
	 * @param list 
	 * 
	 * @returns boolean, whether the user has guessed the answer correctly or
	 * not 
	 * 
	 * 
	 */
	
	public boolean containsCaseInsensitive(String strToCompare, ArrayList<String>list) {
		for(String str:list){
			if(str.equalsIgnoreCase(strToCompare)){
				return(true);
			}
		}
     return(false);
     }
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns String
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public String getCurrent() throws IOException {
		String toReturn = "";
		for(int i = 0; i < current.size(); i ++) {
			toReturn += current.get(i);
		}
		return toReturn;
	}
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns String
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public String getGuessed() throws IOException {
		String toReturn = "";
		for(int i = 0; i < guessed.size(); i ++) {
			toReturn += guessed.get(i);
		}
		return toReturn;
	}
	
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns String
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
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
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param s
	 * 
	 * @returns boolean
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public boolean checkAns(String a) throws IOException {
		if(a.toLowerCase().trim().equalsIgnoreCase(answer.toLowerCase().trim())) {
            guessed.clear();
            current.clear();
			return true;
		}
		return false;
	}
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param a
	 * 
	 * @returns boolean
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public boolean checkGuess(String a) throws IOException {
		if(a.equalsIgnoreCase("check guessed")){
			return true;
		}
		return false;
	}
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void changeTurn(){
		if(twoPlayerMode) {
			if(whosTurn == 1) {
				whosTurn = 0;
			} else {
				whosTurn = 1;
			}
		} else if (whosTurn == 2) {
			whosTurn = 0;
		}	else	{
			whosTurn += 1;
		}
	}
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns int
	 * 
	 * 
	 */
	
	public int whosTurn()	{
		return whosTurn;
	}
	
	/**
	 * 	 
	 * 
	 *
	 * 
	 * @param a
	 * 
	 * @returns String
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
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
                } else {
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
	
	/**
	 * 	 
	 * 
	 * 
	 *
	 * 
	 * @param zero
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void setWinPuzzleCounter(int zero) {
		numberOfPuzzles = zero;
	}
	
	/**
	 * 	 
	 * 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
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
	
	/**
	 * 	 
	 * 
	 *
	 *
	 * 
	 * @param none
	 * 
	 * @returns boolean
	 * 
	 * 
	 */
	
	public boolean win() {	
		if (numberOfPuzzles >= numberOfPuzzlesToWin) {
			return true;
		}
		return false;
	}
	
	/**
	 * 	 
	 * 
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns boolean
	 * 
	 * 
	 */
	
	public boolean winPuzzle() {
		for(int i = 0; i <current.size(); i ++) {
			if(current.get(i) == "*") {
				return false;
			}
		}
        guessed.clear();
		return true;         
	}
	
	/**
	 * 	 
	 * 
	 *
	 *
	 * 
	 * @param saveName
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
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
	        output.write(twoPlayerMode+"");
	        output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 	 
	 * 
	 *
	 *
	 * 
	 * @param saveName
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
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
			line = br.readLine();
			twoPlayerMode = Boolean.valueOf(line.trim());
			br.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
	}
}