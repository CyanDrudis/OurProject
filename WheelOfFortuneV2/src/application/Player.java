package application;


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
	private String name;
	private double money;
        //Player's current round account balance
        //Player's account balance so to say
	
	/**
	 * 	 
	 * 
	 *
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
	 * 
	 *
	 * 
	 * @param none
	 * 
	 * @returns String
	 * 
	 * 
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * 	 
	 * 
	 * 
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
	 * 
	 * 
	 *
	 * 
	 * @param name
	 * 
	 * @returns none
	 * 
	 * 
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * 	 
	 * 
	 *
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
	 * 
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
