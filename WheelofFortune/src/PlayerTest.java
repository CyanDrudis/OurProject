import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	//Testing Methods
		@Test	
		public void test_getter_and_setter_money() {
			Player p = new Player();
			p.setMoney(500);
			assertEquals("Set balance to 500", 500, p.getMoney(), 0.000001);
		}
		
		@Test	
		public void test_getter_and_setter_name() {
			Player p = new Player();
			p.setName("WheelOfFortune");
			assertEquals("Set name to WheelOfFortune", "WheelOfFortune", p.getName());
		}
		
		@Test	
		public void test_deposit_money() {
			Player p = new Player();
			p.deposit(500);
			assertEquals("Deposit 500", 500, p.getMoney(), 0.000001);
		}
		
		@Test	
		public void test_withdraw_money() {
			Player p = new Player();
			p.withdraw(500);
			assertEquals("Withdraw 500", -500, p.getMoney(), 0.000001);
		}
		
		@Test	
		public void test_price_per_vowel() {
			Player p = new Player();
			p.pricePerVowel(3);
			assertEquals("Guessed three vowels, view balance afterwards", -750, p.getMoney(), 0.000001);
		}
	
		

}