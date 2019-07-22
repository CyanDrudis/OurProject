import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI extends Game {

	
	public static void main (String [] args) throws IOException {
		Game g = new Game();
		Puzzle p = new Puzzle();
		
		g.newGame();
		g.importWheel();
		g.setCurrent();
		System.out.print("Welcome to Wheel of Fortune. Take a spin of the Wheel.");
		g.spin();
		Scanner read = new Scanner(System.in);
		String input = read.next();
		char a = read.next().charAt(0);
		g.inputChar(a);
			
		
	}
	
	
}
