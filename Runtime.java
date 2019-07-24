import java.io.IOException;
import java.util.Scanner;

/*********************************************************************************************************************
 * CLASS: Runtime.java
 * 
 * PURPOSE: to be the main engine where the game is created
 * 
 * VARIABLES: 
 *                    
 * NOTES: 
 *********************************************************************************************************************/
		
public class Runtime extends Game {
		
		
		String[] spoke;
		Game g = new Game();
		Puzzle p = new Puzzle();
		//Player p1 = new Player();
		//Player p2 = new Player();
		
		public void main(String[] args) throws IOException {
		
		g.newGame();
		g.importWheel();
		g.setCurrent();
		System.out.print("Welcome to Wheel of Fortune. Take a spin of the Wheel.");
		spoke = g.spin().split(",");
		if (spoke[1] == "loseaturn") {
			loseATurn();
		}

		if (spoke[1] == "bankrupt") {
			bankrupt();
		}
		Scanner read = new Scanner(System.in);
		String input = read.nextLine();
		
		char a = read.next().charAt(0);
		g.inputChar(a); 
		}
		//Check the answer
		//If the answer is right, refresh()
		//If the answer is wrong, changeTurn()
		//If win, deposit value of wheel/get prize
}