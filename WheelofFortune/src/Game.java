import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
//test
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
 *
 *
 *					  -name: a String, the player's name
 *                    -money: a double, the player's 'account' so to say  
 *                    -bonus:   
 *                    -vowelCost:
 *                    -prizeList: 
 *                    
 * NOTES: Negative balance is possible and allowed in Wheel of Fortune, therefore it is not accounted for here.
 *********************************************************************************************************************/

public class Game 
{
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
	private static final double vowelCost = 250;
	private String name;
	//private double money; //Player's account balance so to say
	private ArrayList<Boolean> prizeList = new ArrayList<>(Collections.nCopies(bonus, false));
	
	Puzzle p = new Puzzle();
	Player p1 = new Player();
	Player p2 = new Player();
	Player p3 = new Player();
	ArrayList<Player> players = new ArrayList<Player>();
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void importWheel() throws IOException 
	{
		wheel = p.getWheel();
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void refresh() throws IOException 
	{
		char[] check;
		check = p.getAnswer().toCharArray();
		for(int i = 0; i < check.length; i++) 
		{
			if(check[i] == ' ') 
			{
				current.add(" ");	
			}
			else if(guessed.contains(check[i]))
			{
				current.set(i, check[i]+"");
			}
			else
			{
				current.add("*");
			}
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void newGame() throws IOException 
	{
		answer = p.getAnswer();
		p.newAnswer();
		p.newPuzzle();
		answers = p.getAnswer().toCharArray();
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void newPuzzle() throws IOException 
	{
		answers = p.getAnswer().toCharArray();
		answer = p.getAnswer();
		p.newAnswer();
		p.newPuzzle();
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void bankrupt() 
	{
		players.get(whosTurn).setMoney(0.0);
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void loseATurn()
	{
		if(whosTurn == 2) 
		{
			whosTurn = 0;
		}
		else
		{
			whosTurn += 1;
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void setCurrent() throws IOException 
	{
		char[] check;
		check = p.getAnswer().toCharArray();
		for(int i = 0; i < check.length; i++) 
		{
			if(check[i] == ' ') 
			{
				current.add(" ");	
			}
			else
			{
				current.add("*");
			}
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public getCurrent() throws IOException 
	{
		
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void freePlay(char a) 
	{
		int numOfVowels = 0;
		for(int i = 0; i < current.size(); i++) 
		{
			if(a == answers[i]) 
			{
				numOfVowels++;
			}
		}
		players.get(whosTurn).pricePerVowel(numOfVowels);
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public String spin() 
	{
		Random rand = new Random();
		
		currentSpokeValue = (int) wheel.get(rand.nextInt(wheel.size()));
		
		return wheel.get(rand.nextInt(wheel.size())); //get a random index from the arraylist, may have to change later
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public boolean checkAns(String a) throws IOException 
	{
		if(a.toLowerCase() == p.getAnswer().toLowerCase()) 
		{
			return true;
		}
		return false;
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public void changeTurn()
	{
		if(whosTurn == 2) 
		{
			whosTurn = 0;
		}
		else
		{
			whosTurn += 1;
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public int whosTurn()
	{
		return whosTurn();
	}
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public Boolean inputChar(char a) {
		for (i=0;i<guessed.size();i++){
			if ((a+"") == guessed.get(i)) {
				return false;
			} 
		}
		for (i=0;i<answers.size();i++) {
			if (a == answers.get(i)) {
				return true;
				guessed.add(a+"");
			} 
		} else {
			return false;
		}
	}
	
	/***************************************************************************************
	 * FUNCTION: 
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: 
	 *
	 * INPUT PARAMETERS:  
	 * 
	 *********************************************************************************************/
	public boolean win() 
	{		
		return false;
	}
	
	
	/************************************************************************
	 * Player related methods
	 ************************************************************************/
	/************************************************************************
	 * FUNCTION: getMoney
	 * 
	 * PURPOSE: returns the amount of money in a player's account
	 * 
	 * METHOD: return instance variable money
	 * 
	 * RETURNS: a double
	 ************************************************************************/
	public double getMoney() {
		return money[whosTurn];
	}
	
    /************************************************************************
	 * FUNCTION: getName
	 * 
	 * PURPOSE: return the given name of the player
	 * 
	 * METHOD: return instance variable name
	 * 
	 * RETURNS: a String
	 ************************************************************************/
	public String getName() {
		return name;
	}
	
	/************************************************************************
	 * FUNCTION: setMoney
	 * 
	 * PURPOSE: set the amount of money for the invoking object/instance
	 * 
	 * METHOD: make the instances' money variable equal to what is passed in
	 * 
	 * RETURNS: nothing.
	 * 
	 * INPUT PARAMETERS:  a double
	 ************************************************************************/
	public void setMoney(double money) {
		this.money[whosTurn] = money;
	}
	
	/************************************************************************
	 * Function: setName 
	 * 
	 * Purpose: set the given name for the invoking object/instance
	 * 
	 * Method: makes the object's name variable equal to what is passed in
	 * 
	 * Returns: nothing
	 * 
	 * Input Parameters: a String
	 ************************************************************************/
	public void setName(String name) {
		this.name = name;
	}
	
	/*************************************************************************
	 * Function: deposit
	 * 
	 * Purpose:  deposit a certain amount into the player's money, a placeholder for some type of account
	 * 
	 * Method: add the players money by the amount passed in
	 * 
	 * Returns: nothing
	 * 
	 * Input Parameters: a double 
	 *************************************************************************/
	public void deposit(double amount) {
		this.money[whosTurn] = this.money[whosTurn] + amount;
	}
	
	/*************************************************************************
	 * Function: withdraw 
	 * 
	 * Purpose: take out a certain amount of money from the players account.
	 * 
	 * Method: subtract the money in a player's account by the value passed in.
	 * 		   note: It is possible to have a negative balance in Wheel of Fortune so this is not accounted for here.	
	 * 
	 * Returns: nothing
	 * 
	 * Input Parameters: a double 
	 **************************************************************************/
	public void withdraw(double amount) {
		this.money[whosTurn] = this.money[whosTurn] - amount;
	}
	
	/****************************************************************************
	 * Function: pricePerVowel
	 * 
	 * Purpose: the player has to pay in order to use a vowel //Better description later 
	 * 
	 * Method: the player's account is subtracted by the number of vowels used multiples by the cost which is 250.
	 * 
	 * Returns: nothing
	 * 
	 * Input Parameters: an integer 
	 ******************************************************************************/
	public void pricePerVowel(int numberOfVowels) {
		this.money[whosTurn] = this.money[whosTurn] - numberOfVowels*vowelCost; //Do we need to subtract this.money as well? This will always result in a negative num
	}
}