import java.io.IOException;
import java.util.ArrayList;
public class Game 
{
	private String puzzle;
	private String answer;
	private static ArrayList<Boolean> turns = new ArrayList<Boolean>();
	private static ArrayList<String> wheel = new ArrayList<String>();
	public void bankrupt(Player p) 
	{
		//p.setMoney(0.00);
	}
	public void loseATurn(Player p)
	{
		//p.skipTurn(p)
	}
	public void freePlay(Player p) 
	{
		//p.pricePerVowel(0, 1)
	}
	public void spin() 
	{
		
	}
	public boolean checkAns() 
	{
		return true;
	}
	public void input(char a) 
	{
		
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
