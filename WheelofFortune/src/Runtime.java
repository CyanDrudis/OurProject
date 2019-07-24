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
		
		
		String spoke;
		Game g = new Game();
		Puzzle p = new Puzzle();
		//Player p1 = new Player();
		//Player p2 = new Player();
		
	public void main(String[] args) throws IOException {
		while(win()==false) {
			System.out.println("New puzzle!");
			while(winPuzzle()==false) {
				g.newGame();
				g.importWheel();
				g.setCurrent();
				System.out.println("Welcome to Wheel of Fortune. Take a spin of the Wheel.");
				spoke = g.spin();
				System.out.println("You spun the wheel and landed on" + spoke);
				if (spoke == "loseaturn") {
					loseATurn();
					System.out.println("Sorry you lose a turn!");
				}
				if (spoke == "bankrupt") {
					bankrupt();
					System.out.println("Oh no you went bankrupt!");
				}
				System.out.println("Puzzle:" + g.getCurrent());
				System.out.println("Now guess a letter or try to solve :");
				Scanner read = new Scanner(System.in);
				String input = read.nextLine();
				char check [] = input.toCharArray();
				if(check.length == 1) 
				{
					char a = check[0];
					if(inputChar(a)) 
					{
						System.out.println("Correct!");
					}
					refresh();
				}
				else
				{
					if(g.checkAns(input)) 
					{
						System.out.println("Correct!");
					}
					refresh();
					g.newGame();
				}
		}	}
	}
		//Check the answer
		//If the answer is right, refresh()
		//If the answer is wrong, changeTurn()
		//If win, deposit value of wheel/get prize
}