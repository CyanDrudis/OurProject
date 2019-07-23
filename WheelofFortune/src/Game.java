import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*********************************************************************************************************************
 * CLASS: Game
 * 
 * PURPOSE:
 * 
 * PRIVATE VARIABLES: 
 *                    
 * NOTES: 
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
}