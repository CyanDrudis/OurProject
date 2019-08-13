package application;


import java.util.ArrayList;
import java.util.Collections;

/*********************************************************************************************************************
 * CLASS: Player
 * 
 * PURPOSE: To pass, set and modify relevant information about the player as the game goes on. 
 *          This is represented as a players 'account' (the amount of money they have), their name and the prize list attached to them.
 * 
 * PRIVATE VARIABLES: -money: a double, the player's 'account' so to say  
 *                    
 * NOTES: Negative balance is possible and allowed in Wheel of Fortune, therefore it is not accounted for here.
 *********************************************************************************************************************/

public class Player extends Game{
	private double money;
        //Player's current round account balance
	
	/**
	 * 	 
	 * 
	 * This method is used to return the money instance variable.
	 * This is equivalent to the player's account balance.
	 *
	 * 
	 * @param none
	 * 
	 * @returns double
	 * 
	 * 
	 */
	
	public double getMoney() {
		return money;
	}
	
	/**
	 * 	 
	 * 
	 * This method is used to set the money instance variable. 
	 * A double is passed in and the instance variable money is
	 * updated with this passed in value.
	 *
	 * 
	 * @param money
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	
	/**
	 * 
	 * This method is used to update the player's balance
	 * by a deposited value. As long as the deposited value is greater
	 * than or equal to zero, the player's balance is then updated.
	 * 
	 * @param amount
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void deposit(double amount) {
		if (amount >= 0) {
			this.money = this.money + amount;
		}
	}
	
	/**
	 * 	 
	 * This method is used to withdraw money from a player's
	 * balance. As long the desired amount withdrawn is greater than 0,
	 * the player's balance is charged this amount.
	 * Note: Wheel of Fortune allows for negative balances. Thus, it
	 * is not accounted for here.  
	 *
	 *
	 * 
	 * @param amount
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void withdraw(double amount) {
		if (amount >= 0) {
			this.money = this.money - amount;
		}
	}
	

}
