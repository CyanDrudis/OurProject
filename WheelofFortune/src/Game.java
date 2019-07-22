import java.io.IOException;
import java.util.ArrayList;
public class Game 
{
	private String puzzle;
	private String answer;
	private String current;
	private int whosTurn = 0; //0 = player, 1 = AI, 2 = AI
	private static ArrayList<String> wheel = new ArrayList<String>();
	Puzzle p = new Puzzle();
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
		
	}
	public boolean freePlay() 
	{
		return true;
	}
	public void spin() 
	{
		
	}
	public boolean checkAns() 
	{
		return true;
	}
	public int inputChar(char a) 
	{
		return 1;
	}
	public boolean win() 
	{
		return false;
	}
	public String board() 
	{
		return "this will include which chars have been guessed out of the answer so far";
	}
	public static void main(String[]args) throws IOException 
	{
		Puzzle p = new Puzzle();
		wheel = p.getWheel();
		//(below)tests for wheel being imported
		for(int i = 0; i < wheel.size(); i++) 
		{
			System.out.println(wheel.get(i));
		}
	}
}
