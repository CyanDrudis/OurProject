
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
	private static ArrayList<String> wheel = new ArrayList<String>();
	private static ArrayList<String> current = new ArrayList<String>();
	private static ArrayList<String> guessed = new ArrayList<String>();
	private char[] answers;
	private double[] money;
	private static final int bonus =5;
	private static final double vowelCost = 50;
	private static final int numberOfPuzzlesToWin = 3;
	private int numberOfPuzzles = -1;
	private String name;
        private int randomNumForWheel;
	//private double money; //Player's account balance so to say
	//private ArrayList<Boolean> prizeList = new ArrayList<>(Collections.nCopies(bonus, false));
	
	Puzzle p = new Puzzle();

	ArrayList<Player> players = new ArrayList<Player>();
        public int getRandomNumForWheel(){
            return randomNumForWheel;
        }
	/***************************************************************************************
	 * FUNCTION: Importing the wheel
	 * 
	 * DESCRIPTION: importing the wheel from a text file containing all the wheel's 
	 * options using the function in the puzzle class
	 * 
	 * METHOD: importWheel()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void
	 * 
	 *********************************************************************************************/
	public void importWheel() throws IOException {
		wheel = p.getWheel();
	}
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * DESCRIPTION: 
	 * 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:
	 * 
	 *********************************************************************************************/
	public String getPuzzle() {
		return puzzle;
	}
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * DESCRIPTION: 
	 * 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:
	 * 
	 *********************************************************************************************/
	public double getBal() {
		return players.get(whosTurn).getMoney();
	}
	/***************************************************************************************
	 * FUNCTION: Refresh the current string array to display to the user what they've guessed/what is
	 * left to guess
	 * 
	 * DESCRIPTION: This method takes the answer and creates a character array to which it checks the 
	 * with the guessed string array, and fills in the index where the guess matches the answer and
	 * the rest with asterisks
	 * 
	 * METHOD: refresh()
	 * 
	 * RETURNS: void
	 *
	 * INPUT PARAMETERS: void
	 * 
	 *********************************************************************************************/
//	public void refresh() throws IOException {
//		char[] check;
//		check = answer.toCharArray();
//		ArrayList<String> current = new ArrayList<String>();
//		for(int i = 0; i < check.length; i++) {
//			if(check[i] == ' ') {
//				current.add(" ");	
//			}else if(guessed.contains((check[i]+""))) {
//				for(int j = 0; j < guessed.size(); j++) {
//					if (guessed.get(j).equals(check[i]+"")) {
//						current.add(guessed.get(j));
//					}
//				}
////			} else if(guessed.contains(check[i])){
////				current.set(i, check[i]+"");
//			} else {
//				current.add("*");
//			}
//		}
//	}
	
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
		puzzle = p.getPuzzle();
		answer = p.getAnswer();
		p.newPuzzle();
		p.newAnswer();
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
		answers = p.getAnswer().toCharArray();
		answer = p.getAnswer();
		p.newAnswer();
		p.newPuzzle();
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
		players.get(whosTurn).setMoney(0.0);
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
		if(a.toLowerCase().equalsIgnoreCase(answer.toLowerCase())) {
                        guessed.clear();
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
		}/*
		for (int i=0;i<answers.size();i++) { //change from answers.size() to answers.length() ?
			if (a == answers.get(i)) {       //change from answers.get(i) to answers[i]
				return true;
				guessed.add(a+"");
			}
		}*/
        boolean there = false;
		for (int i = 0; i < answers.length; i++) {
			if ((a+"").equalsIgnoreCase(answers[i]+"")) {
                            there = true;
				guessed.add(a+"");
                                if(a == 'a' ||a == 'e' ||a == 'i' ||a == 'o' || a =='u'){
                                    players.get(whosTurn).withdraw(vowelCost);
                                }
                                else{
				players.get(whosTurn).deposit(currentSpokeValue);
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
		if (numberOfPuzzles == numberOfPuzzlesToWin) {
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
//	
//	/************************************************************************
//	 * Player related methods
//	 ************************************************************************/
//	/************************************************************************
//	 * FUNCTION: getMoney
//	 * 
//	 * DESCRIPTION: returns the amount of money in a player's account
//	 * 
//	 * METHOD: return instance variable money
//	 * 
//	 * RETURNS: a double
//	 ************************************************************************/
//	public double getMoney() {
//		return money[whosTurn];
//	}
//	
//    /************************************************************************
//	 * FUNCTION: getName
//	 * 
//	 * DESCRIPTION: return the given name of the player
//	 * 
//	 * METHOD: return instance variable name
//	 * 
//	 * RETURNS: a String
//	 ************************************************************************/
//	public String getName() {
//		return name;
//	}
//	
//	/************************************************************************
//	 * FUNCTION: setMoney
//	 * 
//	 * DESCRIPTION: set the amount of money for the invoking object/instance
//	 * 
//	 * METHOD: make the instances' money variable equal to what is passed in
//	 * 
//	 * RETURNS: nothing.
//	 * 
//	 * INPUT PARAMETERS:  a double
//	 ************************************************************************/
//	public void setMoney(double money) {
//		this.money[whosTurn] = money;
//	}
//	
//	/************************************************************************
//	 * FUNCTION: setName 
//	 * 
//	 * DESCRIPTION: set the given name for the invoking object/instance
//	 * 
//	 * METHOD: makes the object's name variable equal to what is passed in
//	 * 
//	 * RETURNS: nothing
//	 * 
//	 * INPUT PARAMETERS: a String
//	 ************************************************************************/
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	/*************************************************************************
//	 * FUNCTION: deposit
//	 * 
//	 * DESCRIPTION:  deposit a certain amount into the player's money, a placeholder for some type of account
//	 * 
//	 * METHOD: add the players money by the amount passed in
//	 * 
//	 * RETURNS: nothing
//	 * 
//	 * INPUT PARAMETERS: a double 
//	 *************************************************************************/
//	public void deposit(double amount) {
//		this.money[whosTurn] = this.money[whosTurn] + amount;
//	}
//	
//	/*************************************************************************
//	 * FUNCTION: withdraw 
//	 * 
//	 * DESCRIPTION: take out a certain amount of money from the players account.
//	 * 
//	 * METHOD: subtract the money in a player's account by the value passed in.
//	 * 		   note: It is possible to have a negative balance in Wheel of Fortune so this is not accounted for here.	
//	 * 
//	 * RETURNS: nothing
//	 * 
//	 * INPUT PARAMETERS: a double 
//	 **************************************************************************/
//	public void withdraw(double amount) {
//		this.money[whosTurn] = this.money[whosTurn] - amount;
//	}
//	
//	/****************************************************************************
//	 * FUNCTION: pricePerVowel
//	 * 
//	 * DESCRIPTION: the player has to pay in order to use a vowel //Better description later 
//	 * 
//	 * METHOD: the player's account is subtracted by the number of vowels used multiples by the cost which is 250.
//	 * 
//	 * RETURNS: nothing
//	 * 
//	 * INPUT PARAMETERS: an integer 
//	 ******************************************************************************/
//	public void pricePerVowel(int numberOfVowels) {
//		this.money[whosTurn] = this.money[whosTurn] - numberOfVowels*vowelCost; 
//	}
}