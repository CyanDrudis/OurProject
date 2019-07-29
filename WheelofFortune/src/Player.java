

import java.util.ArrayList;
import java.util.Collections;

/*********************************************************************************************************************
 * CLASS: Player
 * 
 * PURPOSE: To pass, set and modify relevant information about the player as the game goes on. 
 *          This is represented as a players 'account' (the amount of money they have), their name and the prize list attached to them.
 * 
 * PRIVATE VARIABLES: -name: a String, the player's name
 *                    -money: a double, the player's 'account' so to say 
 *                    -vowelCost: a constant double, cost to use a vowel
 *                    
 * NOTES: Negative balance is possible and allowed in Wheel of Fortune, therefore it is not accounted for here.
 *********************************************************************************************************************/

public class Player extends Game{
	private static final double vowelCost = 250;
	private String name;
	private double money; //Player's account balance
	
	/************************************************************************
	 * METHOD: getMoney
	 * 
	 * PURPOSE: returns the amount of money in a player's account
	 * 
	 * DESCRIPTION: return instance variable money
	 * 
	 * RETURNS: a double
	 ************************************************************************/
	public double getMoney() {
		return money;
	}
	
    /************************************************************************
	 * METHOD: getName
	 * 
	 * PURPOSE: return the given name of the player
	 * 
	 * DESCRIPTION: return instance variable name
	 * 
	 * RETURNS: a String
	 ************************************************************************/
	public String getName() {
		return name;
	}
	
	/************************************************************************
	 * METHOD: setMoney
	 * 
	 * PURPOSE: set the amount of money for the invoking object/instance
	 * 
	 * DESCRIPTION: make the instances' money variable equal to what is passed in
	 * 
	 * RETURNS: nothing.
	 * 
	 * INPUT PARAMETERS:  a double
	 ************************************************************************/
	public void setMoney(double money) {
		this.money = money;
	}
	
	/************************************************************************
	 * METHOD: setName 
	 * 
	 * PURPOSE: set the given name for the invoking object/instance
	 * 
	 * DESCRIPTION: makes the object's name variable equal to what is passed in
	 * 
	 * RETURNS: nothing
	 * 
	 * INPUT PARAMETERS: a String
	 ************************************************************************/
	public void setName(String name) {
		this.name = name;
	}
	
	/*************************************************************************
	 * METHOD: deposit
	 * 
	 * Purpose:  deposit a certain amount into the player's money, a placeholder for some type of account
	 * 
	 * DESCRIPTION: add the players money by the amount passed in
	 * 
	 * RETURNS: nothing
	 * 
	 * INPUT PARAMETERS: a double 
	 *************************************************************************/
	public void deposit(double amount) {
		this.money = this.money + amount;
	}
	
	/*************************************************************************
	 * FUNCTION: withdraw 
	 * 
	 * PURPOSE: take out a certain amount of money from the players account.
	 * 
	 * METHOD: subtract the money in a player's account by the value passed in.
	 * 		   note: It is possible to have a negative balance in Wheel of Fortune so this is not accounted for here.	
	 * 
	 * RETURNS: nothing
	 * 
	 * INPUT PARAMETERS: a double 
	 **************************************************************************/
	public void withdraw(double amount) {
		this.money = this.money - amount;
	}
	
	/****************************************************************************
	 * FUNCTION: pricePerVowel
	 * 
	 * PURPOSE: the player has to pay in order to use a vowel //Better description later 
	 * 
	 * METHOD: the player's account is subtracted by the number of vowels used multiples by the cost which is 250.
	 * 
	 * RETURNS: nothing
	 * 
	 * INPUT PARAMETERS: an integer 
	 ******************************************************************************/
	public void pricePerVowel(int numberOfVowels) {
		this.money = this.money - numberOfVowels*vowelCost; 
	}
}
