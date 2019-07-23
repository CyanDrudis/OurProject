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
 * PRIVATE VARIABLES: 
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
	private String answer;
	private int whosTurn = 0; //0 = player, 1 = AI, 2 = AI
	private static ArrayList<String> wheel = new ArrayList<String>();
	private static ArrayList<String> current = new ArrayList<String>();
	private static ArrayList<String> guessed = new ArrayList<String>();
	private char[] answers;
	
	private static final int bonus =5;
	private static final double vowelCost = 250;
	private String name;
	private double money; //Player's account balance so to say
	private ArrayList<Boolean> prizeList = new ArrayList<>(Collections.nCopies(bonus, false));
	
	Puzzle p = new Puzzle();
	
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
		Player main = new Player();
		Player AI1 = new Player();
		Player AI2 = new Player();
		players.add(main);
		players.add(AI1);
		players.add(AI2);
		answers = p.getAnswer().toCharArray();
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
	public void bankrupt(Player p) 
	{
		p.setMoney(0.00);
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
	public void loseATurn(Player p)
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
	public void spin() 
	{
		Random rand = new Random();
		wheel.get(rand.nextInt(wheel.size())); //get a random index from the arraylist, may have to change later
		
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
	public void inputChar(char a) 
	{
		guessed.add(a+"");
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
	//[0] = cruise, [1] = wildcard, [2] = 1/2 car (1st half) [3] = 1/2 car (2nd half)
	//[4] = 1 million dollar prize
	
	/* FUNCTION: getPrizeListBoolean
	 * 
	 * PURPOSE: 
	 * 
	 * METHOD: 
	 * 
	 * RETURNS: a Boolean
	 *
	 * INPUT PARAMETERS:  an integer
	 */
	public Boolean getPrizeListBoolean(int index) {
		return prizeList.get(index);
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
		return money;
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
		this.money = money;
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
		this.money = this.money + amount;
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
		this.money = this.money - amount;
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
		this.money = this.money - numberOfVowels*vowelCost; //Do we need to subtract this.money as well? This will always result in a negative num
	}
}