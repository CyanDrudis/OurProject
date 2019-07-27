import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	
	@Test
	public void test_getter_and_setter_money() {
		Game g = new Game();
		Player p = new Player();
		g.players.add(p);
		p.setMoney(500);
		assertEquals("Set balance to 500", 500, g.getBal(), 0.000001);
	}
	
	@Test
	public void test_bankrupt() {
		Game g = new Game();
		Player p = new Player();
		g.players.add(p);
		p.setMoney(500);
		g.bankrupt();
		assertEquals("Set balance to 0", 0, g.getBal(), 0.000001);
	}
	
	@Test
	public void test_loseATurn() {
		Game g = new Game();
		Player p = new Player();
		g.loseATurn();
		assertEquals("Set balance to 0", 1, g.whosTurn(), 0.000001);
	}
	
	@Test
	public void test_changeTurn() {
		Game g = new Game();
		Player p = new Player();
		g.changeTurn();
		assertEquals("Set balance to 0", 1, g.whosTurn(), 0.000001);
	}
	
	@Test
	public void test_win() {
		Game g = new Game();
		g.winPuzzleCounter();
		g.winPuzzleCounter();
		g.winPuzzleCounter();
		assertEquals("Check for win", true , g.win());
	}

}
