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
		public void test_deposit_more_money() {
			Player p = new Player();
			p.deposit(500);
			p.deposit(1000500);
			p.deposit(1);
			p.deposit(2);
			p.deposit(15);
			assertEquals("Deposit 500, 1000500, 1, 2 and 15", 1001018, p.getMoney(), 0.000001);
		}
		
		@Test	
		public void test_withdraw_more_money() {
			Player p = new Player();
			p.withdraw(500);
			p.withdraw(40);
			p.withdraw(9200);
			p.withdraw(6);
			assertEquals("Withdraw 500, 40, 9200, 6 and 79483", -89229, p.getMoney(), 0.000001);
		}
		
		@Test
		public void test_deposit_negative_money() {
			Player p = new Player();
			p.setMoney(500);
			p.deposit(-500);
			assertEquals("Set money to 500 and attempt to deposit -500", 500, p.getMoney(), 0.000001);
		}
		
		@Test
		public void test_withdraw_negative_money() {
			Player p = new Player();
			p.setMoney(500);
			p.withdraw(-500);
			assertEquals("Set money to 500 and attempt to deposit -500", 500, p.getMoney(), 0.000001);
		}
}
