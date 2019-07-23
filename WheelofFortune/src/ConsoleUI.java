import java.io.IOException;
import java.util.Scanner;

/*********************************************************************************************************************
 * CLASS: Console UI
 * 
 * PURPOSE:
 * 
 * VARIABLES: 
 *                    
 * NOTES: 
 *********************************************************************************************************************/
public class ConsoleUI extends Game {

	
	public static void main (String [] args) throws IOException {
		String spoke = "";
		Game g = new Game();
		Puzzle p = new Puzzle();
		Player p1 = new Player();
		
		
		
		g.newGame();
		g.importWheel();
		g.setCurrent();
		System.out.print("Welcome to Wheel of Fortune. Take a spin of the Wheel.");
		spoke = g.spin();
		if (spoke == "loseaturn") {
			loseATurn();
		}

		if (spoke == "bankrupt") {
			bankrupt();
		}
		Scanner read = new Scanner(System.in);
		String input = read.next();
		char a = read.next().charAt(0);
		g.inputChar(a);
		//Check the answer
		//If the answer is right, refresh()
		//If the answer is wrong, changeTurn()
		//If win, deposit value of wheel/get prize

			
		
	}
	
	
}
