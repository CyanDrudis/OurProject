package application;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
		g.changeTurn();
		g.changeTurn();
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
	public void test_win_and_winPuzzleCounter() {
		Game g = new Game();
		g.winPuzzleCounter();
		g.winPuzzleCounter();
		g.winPuzzleCounter();
		g.winPuzzleCounter();
		assertEquals("Check for win", true, g.win());
	}
	
//	@Test 
//	public void test_getPuzzle() {
//		Game g = new Game();
//		assertEquals("Check for win", null , g.getPuzzle());
//	}
	
	@Test
	public void test_yes_win_winPuzzle() {
		Game g = new Game();
		assertEquals("Check for win", true , g.winPuzzle());
	}
	
//	@Test
//	public void test_no_win_winPuzzle() throws IOException {
//		Game g = new Game();
//		g.newGame();
//		g.importWheel();
//		g.setCurrent();
//		
//		assertEquals("Check for win", false , g.winPuzzle());
//	}
	

}
