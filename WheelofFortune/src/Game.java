import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
	public void importWheel() throws IOException 
	{
		wheel = p.getWheel();
	}
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
	public void newPuzzle() throws IOException 
	{
		answers = p.getAnswer().toCharArray();
		answer = p.getAnswer();
		p.newAnswer();
		p.newPuzzle();
	}
	public void bankrupt(Player p) 
	{
		p.setMoney(0.00);
	}
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
	public void spin() 
	{
		Random rand = new Random();
		wheel.get(rand.nextInt(wheel.size())); //get a random index from the arraylist, may have to change later
		
	}
	public boolean checkAns(String a) throws IOException 
	{
		if(a.toLowerCase() == p.getAnswer().toLowerCase()) 
		{
			return true;
		}
		return false;
	}
	
	public void changeTurn();
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
	
	public void inputChar(char a) 
	{
		guessed.add(a+"");
	}
	public boolean win() 
	{		
		return false;
	}
}