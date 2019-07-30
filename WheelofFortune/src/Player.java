

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
 *                    -bonus:   
 *                    -vowelCost:
 *                    -prizeList: 
 *                    
 * NOTES: Negative balance is possible and allowed in Wheel of Fortune, therefore it is not accounted for here.
 *********************************************************************************************************************/

public class Player extends Game{
	private static final int bonus =5;
	private String name;
	private double money;
        //Player's current round account balance
        private double totalMoney;
        //Player's account balance so to say
	
	/************************************************************************
	 * FUNCTION: getMoney
	 * 
	 * PURPOSE: returns the amount of money in a player's account
	 * 
	 * METHOD: return instance variable money
	 * 
	 * RETURNS: a double
	 ************************************************************************/
	public double getTotalMoney() {
		return totalMoney;
	}
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
		if (amount >= 0) {
			this.money = this.money + amount;
		}
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
		if (amount >= 0) {
			this.money = this.money - amount;
		}
	}
	

}
