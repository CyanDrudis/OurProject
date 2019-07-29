
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
	private static final int numberOfPuzzlesToWin = 3;
	private int numberOfPuzzles = -1;
	
	//private double money; //Player's account balance so to say
	//private ArrayList<Boolean> prizeList = new ArrayList<>(Collections.nCopies(bonus, false));
	
	Puzzle p = new Puzzle();

	ArrayList<Player> players = new ArrayList<Player>();
	/***************************************************************************************
	 * METHOD: importWheel()
	 * 
	 * PURPOSE: Importing the wheel
	 * 
	 * DESCRIPTION: importing the wheel from a text file containing all the wheel's 
	 * options using the function in the puzzle class
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
	 * METHOD: 
	 * 
	 * PURPOSE: 
	 * 
	 * DESCRIPTION: 
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
	 * METHOD: 
	 * 
	 * PURPOSE: 
	 * 
	 * DESCRIPTION: 
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
	 * METHOD: refresh()
	 * 
	 * PURPOSE: Refresh the current string array to display to the user what they've guessed/what is
	 * left to guess
	 * 
	 * DESCRIPTION: This method takes the answer and creates a character array to which it checks the 
	 * with the guessed string array, and fills in the index where the guess matches the answer and
	 * the rest with asterisks
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
	 * METHOD: newGame
	 * 
	 * PURPOSE: creating a brand new game (puzzle and answer)
	 * 
	 * DESCRIPTION: creates a brand new game for the player to try and win, chooses a puzzle,
	 * then chooses an answer from that puzzle category, creates three player accounts
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
	 * METHOD: newPuzzle
	 * 
	 * PURPOSE: Create a new puzzle and obtain an answer from that puzzle
	 * 
	 * DESCRIPTION: transfer answer to character array, create new answer and puzzle
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
	 * METHOD: bankrupt
	 * 
	 * PURPOSE: Reset users money upon landing on bankrupt section of the wheel
	 * 
	 * DESCRIPTION: sets player who lands on the bankrupt section's money to 0
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
	 * METHOD: loseATurn
	 * 
	 * PURPOSE: Cycle through players turn	
	 * 
	 * DESCRIPTION: If whosTurn ever equals 2, we reset it to 0, otherwise we increment by 1 until the reset occurs again.
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
	 * METHOD: setCurrent
	 * 
	 * PURPOSE: Set the base string to which the user is going to guess characters to fill up the word 
	 * 
	 * DESCRIPTION: Go through each index of the length of the answer, and add either blank spaces or
	 * asterisks 
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
	 * METHOD: containsCaseInsensitive
	 * 
	 * PURPOSE: checks case sensitivity
	 * 
	 * DESCRIPTION: checks each string in the list for case sensitivity.
	 * 
	 * RETURNS: a boolean
	 *
	 * INPUT PARAMETERS: a String and an Array List of Strings
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
	 * METHOD: getCurrent
	 * 
	 * PURPOSE: get the current string
	 * 
	 * DESCRIPTION: Returns the current string by converting the contents of the string array to a string
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
	 * METHOD: getGuessed
	 * 
	 * PURPOSE: get the guessed string or the input
	 * 
	 * DESCRIPTION: Returns the current string by converting the contents of the string array to a string
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
	 * METHOD: spin
	 * 
	 * PURPOSE: gets a random index of the wheel. i.e. lands on a spoke of the wheel
	 * 
	 * DESCRIPTION: gets a random index of the wheel, which is the 'prize' that the user will be
	 * gaining money from if they guess characters correctly, or if it's a special type of prize
	 * such as bankrupt/1/2car, they will be receiving that 
	 * 
	 * RETURNS: a String
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public String spin() throws IOException {
		Random rand = new Random();
		int randomNumForWheel = rand.nextInt(wheel.size());
                setCurrent();
		if(!wheel.get(randomNumForWheel).equals("bankrupt")&& !wheel.get(randomNumForWheel).equals("loseaturn") && !wheel.get(randomNumForWheel).equals("freespin")) {
			currentSpokeValue = Integer.valueOf(wheel.get(randomNumForWheel));
		}
		return wheel.get(randomNumForWheel);
	}
	
	/***************************************************************************************
	 * METHOD: checkAns
	 * 
	 * PURPOSE: checks string guessed to the answer
	 * 
	 * DESCRIPTION: when the user is ready to guess the entire word, they will input an answer 
	 * of type string which will be compared to the actual answer
	 * 
	 * RETURNS: a Boolean
	 *
	 * INPUT PARAMETERS: a String
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
	 * METHOD: checkGuess
	 * 
	 * PURPOSE: allows user to check what letters they have guessed
	 * 
	 * DESCRIPTION: checks string to return guessed answer list
	 * 
	 * RETURNS: a Boolean
	 *
	 * INPUT PARAMETERS: a String
	 * 
	 *********************************************************************************************/
	public boolean checkGuess(String a) throws IOException {
		if(a.equalsIgnoreCase("check guessed")){
			return true;
		}
		return false;
	}
	
	/***************************************************************************************
	 * METHOD: changeTurn
	 * 
	 * PURPOSE: changes who's turn it is
	 * 
	 * DESCRIPTION: If whosTurn ever equals 2, we reset it to 0, otherwise we increment by 1 until the reset occurs again.
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
	 * METHOD: whosTurn
	 * 
	 * PURPOSE: lets user know who's turn it is
	 * 
	 * DESCRIPTION: returns which user's turn it is
	 * 
	 * RETURNS: an Integer
	 *
	 * INPUT PARAMETERS: none 
	 * 
	 *********************************************************************************************/
	public int whosTurn()	{
		return whosTurn;
	}
	/***************************************************************************************
	 * METHOD: inputChar
	 * 
	 * PURPOSE: checks inputted character with the guessed array and answer
	 * 
	 * DESCRIPTION: checks if the guessed array contains a space or an asterisk. Then compares inputted character 
	 * 	to the guessed array to see if it's already been guessed, then it is compared it to the answer, and added 
	 *  to the guessed array. If any of the inputs are a vowel, then the current spoke value is charged to the player's
	 *  account. Otherwise it deposits the spoke value into the player's account.
	 * 
	 * METHOD: inputChar()
	 * 
	 * RETURNS: Boolean
	 *
	 * INPUT PARAMETERS:  char
	 * 
	 *********************************************************************************************/
	public String inputChar(char a) {
            if(!guessed.contains(a+"")){
        	guessed.add(a+""); 
            }
		if(!current.contains("*")){
                    guessed.clear();
                    return "puzzleComplete";
                }
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
			if (a == answers[i]) {
				guessed.add(a+"");
                                if(a == 'a' ||a == 'e' ||a == 'i' ||a == 'o' || a =='u'){
                                    players.get(whosTurn).withdraw(currentSpokeValue);
                                }
                                else{
				players.get(whosTurn).deposit(currentSpokeValue);
                                }
                there = true;
			}
		}
        if(there){
        	return "there";
        }
        
		return "notThere";
	}
	
	/***************************************************************************************
	 * METHOD: winPuzzleCounter
	 * 
	 * PURPOSE: Increasing the puzzle counter
	 * 
	 * DESCRIPTION: Increasing the puzzle counter to see when the win condition has been reached
	 * 
	 * RETURNS: void	
	 *
	 * INPUT PARAMETERS: none
	 * 
	 *********************************************************************************************/
	public void winPuzzleCounter() {
		
		numberOfPuzzles = numberOfPuzzles + 1;
	}
	
	/***************************************************************************************
	 * METHOD: win
	 * 
	 * PURPOSE: Determine if the conditions for a win is reached
	 * 
	 * DESCRIPTION: when the number of puzzles to win has been reached, the method will return
	 * true 
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
	 * METHOD: winPuzzle
	 * 
	 * PURPOSE: Determines if the user completed the puzzle
	 * 
	 * DESCRIPTION: In a for loop, checks to see if anything in the current arrayList still contains a star, if so return false, 
	 * the user has not completed the puzzle. Otherwise return true. 
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
		guessed.clear(); //May or may not delete
		return true;
	}
}

/***************************************************************************************
 * METHOD: freePlay
 * 
 * PURPOSE:  Allows user to guess vowels and withdraws money from their bank account according
 * to the number of vowels that they guess
 * 
 * DESCRIPTION: Compares guessed vowel to the answer character array
 * 
 * METHOD: freePlay()
 * 
 * RETURNS: void
 *
 * INPUT PARAMETERS: void  
 * 
 *********************************************************************************************
public void freePlay(char a) {
	int numOfVowels = 0;
	for(int i = 0; i < current.size(); i++) {
		if(a == answers[i]) {
			numOfVowels++;
		}
	}
	players.get(whosTurn).pricePerVowel(numOfVowels);
}

*/