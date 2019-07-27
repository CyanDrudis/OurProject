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
		
		
		
		//Player p1 = new Player();
		//Player p2 = new Player();
		
	public static void main(String[] args) throws IOException {
		Game g = new Game();
		while(g.win()==false) {
		String spoke;
		g.newGame();
		g.importWheel();
		g.setCurrent();
			System.out.println("New puzzle!");
			while(!g.winPuzzle()) {
				
				System.out.println("Welcome to Wheel of Fortune. Take a spin of the Wheel.");
				spoke = g.spin();
				System.out.println("You spun the wheel and landed on " + spoke);
				if (spoke == "loseaturn") {
					g.loseATurn();
					System.out.println("Sorry you lose a turn!");
				}
				if (spoke == "bankrupt") {
					g.bankrupt();
					System.out.println("Oh no you went bankrupt!");
				}
				int playersTurn = g.whosTurn() + 1;
				System.out.println("Player " + playersTurn +" Turn!");
				System.out.println("Category: " + g.getPuzzle());
				System.out.println("Puzzle: " + g.getCurrent());
				System.out.println("Balance: " + g.getBal());
				System.out.println("Now guess a letter or try to solve :");
				Scanner read = new Scanner(System.in);
				String input = read.nextLine();
				char check [] = input.toCharArray();
				if (input == "check guessed") {
					System.out.println(g.getGuessed());
				}
				if(check.length == 1) 
				{
					char a = check[0];
					if(g.inputChar(a) == "there") 
					{
						System.out.println("Correct!");
					} else if (g.inputChar(a) == "notThere"){
						System.out.println("Letter is not in word!");
						g.changeTurn();
					} else if (g.inputChar(a) == "alreadyThere") {
						System.out.println("You've already guessed this letter");
					}
					g.refresh();
				}
				else
				{
					if(g.checkAns(input)) 
					{
						System.out.println("Correct!");
					} else {
						System.out.println("Incorrect!");
						g.changeTurn();
					}
					g.refresh();
					//g.newGame();
				}
			}
		}	
	}
		//Check the answer
		//If the answer is right, refresh()
		//If the answer is wrong, changeTurn()
		//If win, deposit value of wheel/get prize
}