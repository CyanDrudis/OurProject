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
			System.out.println("Welcome to Wheel of Fortune. Take a spin of the Wheel.");
			String spoke;
			g.newGame();
			g.importWheel();
			g.setCurrent();
            g.winPuzzleCounter();
			System.out.println("New puzzle!");
			while(!g.winPuzzle()) {
                            spoke = g.spin();
                            while(spoke.equals("freespin")){
                                System.out.println("You spun the wheel and... Free Spin! Spinning again...");
                                spoke = g.spin();
                            }
                int playersTurn = g.whosTurn() + 1;
            if (spoke.equals("loseaturn")) {
            	g.loseATurn();
				System.out.println("You spun the wheel and... Sorry you lose a turn!");
                playersTurn = g.whosTurn() + 1;
                System.out.println("Player "+ playersTurn +" next!");
            } else {
            	 if (spoke.equals("bankrupt")) {
                 g.bankrupt();
                 System.out.println("You spun the wheel and... Oh no you went bankrupt!" );
                 playersTurn = g.whosTurn() + 1;
                 System.out.println("Player "+ playersTurn +" next!");
            	 } else {
                     System.out.println("You spun the wheel and landed on $" + spoke);
                     System.out.println("Player " + playersTurn +" Turn" + " | Category: " + g.getPuzzle() + " | Balance: " + g.getBal());
                     System.out.println("Puzzle: " + g.getCurrent());
                     System.out.println("Now guess a letter or try to solve :");
                     Scanner read = new Scanner(System.in);
                     String input = read.nextLine();
                     char check [] = input.toCharArray();
                     
                     if(input.equals("Quit")) {
                    	 System.exit(0);
                     } else if (input.equals("quit")){
                    	 System.exit(0);
                     }
                     
                     if (check.length == 1) {
                     	char a = check[0];
                    	String outcome = g.inputChar(a);
                     if(outcome.equals("there")) {
                     	System.out.println("Correct!");
                     } else if (outcome.equals("notThere")){
                    	 System.out.println("Letter is not in puzzle!");
                     	 g.changeTurn();
                     } else if (outcome.equals("alreadyThere")) {
                    	 System.out.println("That letter has already been guessed!");
                         g.changeTurn();
                     } else if (outcome.equals("puzzleComplete")){
                         playersTurn = g.whosTurn() + 1;
                         System.out.println("Correct! Player "+ playersTurn + " solved the puzzle");
                     }
                     //g.refresh();
                     } else {
                         if (g.checkAns(input)) {
                             playersTurn = g.whosTurn() + 1;
                         	System.out.println("Correct! Player "+ playersTurn + " solved the puzzle");
                         } else {
                        	 System.out.println("Incorrect!");
                           	 g.changeTurn();
                         }
                         //g.refresh();
                         //g.newGame();
                     }
            	 }
            }
			}
			int playersTurn = g.whosTurn() + 1;
            System.out.println("Congratulations player "+ playersTurn + " you successfully solved the puzzle!");
 
            if(g.win()) {
            	if (g.whosTurn() == 2 ){
            		g.changeTurn();
            	} else if (g.whosTurn() == 1) {
            		g.changeTurn();
            		g.changeTurn();
            	}
            	for(int i = 0; i < g.getPlayerListSize(); i++) {
            		System.out.println("Player " + (g.whosTurn()+1) + "'s balance: " + g.getBal());
            		g.changeTurn();
            	}
            	
            	int highest = g.getHighestBalPlayer() + 1;
            	
            	System.out.println("Congratulations Player "+ highest + " you won the game!");
            	System.out.println("Would you like to continue? Y/N");
            	Scanner read = new Scanner(System.in);
                String Continue = read.nextLine();
                if (Continue.equals("Y")) {
                	g.setWinPuzzleCounter(0);
                	for(int i = 0; i < g.getPlayerListSize(); i++) {
                		g.setTurn(0);
                		g.setBal(i,0);
                	}
                	
                } else if (Continue.equals("N")) {
                	System.exit(0);
                }
            }
		}	
	}
		//Check the answer
		//If the answer is right, refresh()
		//If the answer is wrong, changeTurn()
		//If win, deposit value of wheel/get prize
}