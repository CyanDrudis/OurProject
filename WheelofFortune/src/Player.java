import java.util.ArrayList;
import java.util.Collections;


public class Player extends Game{
	private String name;
	private double money;
	private ArrayList<Boolean> prizeList = new ArrayList<>(Collections.nCopies(5, false));
	
	//[0] = cruise, [1] = wildcard, [2] = 1/2 car (1st half) [3] = 1/2 car (2nd half)
	//[4] = 1 million dollar prize
	public Boolean getPrizeListBoolean(int index) 
	{
		return prizeList.get(index);
	}
	public double getMoney() 
	{
		return money;
	}
	public String getName()
	{
		return name;
	}
	
	public void setMoney(double money) 
	{
		this.money = money;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public void deposit(double amount) 
	{
		this.money = this.money + amount;
	}
	public void withdraw(double amount) 
	{
		this.money = this.money - amount;
	}
	public void pricePerVowel(int numberOfVowels, int pricePerVowel) 
	{
		this.money = this.money - numberOfVowels*pricePerVowel;
	}
	
	 
}
