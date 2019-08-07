package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*********************************************************************************************************************
 * CLASS: Puzzle
 * 
 * PURPOSE: To import the wheel, puzzle and answers from text files. Along with choosing a random puzzle and answer.
 * 
 * PRIVATE VARIABLES: 
 * 
 * answers:ArrayList<String>: a List of the answers, each line has multiple answers per category separated by ','
 * each index in this array correlates with an index in the puzzle array
 * 
 * puzzle:ArrayList<String>: a List of the puzzle categories, one on each index.
 * 
 * wheel:ArrayList<String>: a List of the wheel which includes how much money the player will receive for each correct
 * consonant.
 * 
 * randomIntForPuzzle:int : a variable that is to be assigned a randomly generated number within the indexes of the puzzle array
 * 
 * randomIntForAnswer:int : a variable that is to be assigned a randomly generated number within the indexes of the answer array
 * 
 *                    
 * NOTES: 
 *********************************************************************************************************************/


public class Puzzle {
	private ArrayList<String> answers = new ArrayList <String>();
	private ArrayList<String> puzzle = new ArrayList <String>();
	private ArrayList<String> wheel = new ArrayList <String>();
	private int randomIntForPuzzle;
	private int randomIntForAnswer;
	Random rand = new Random();
	
	/**
	 * This method generates a new random integer for a new random puzzle,
	 * passes the size of the puzzle array to the rand method to generate a random number
	 * and assigns it to RandomIntForPuzzle
	 * 
	 * @param none
	 *
	 * @returns none
	 * 
	 *
	 **/
	
	public void newPuzzle() {
		randomIntForPuzzle = rand.nextInt(puzzle.size());
	}
	
	/**
	 * 	 
	 * This method generates a random integer for a new random answer. The method uses the same integer created for the 
	 * puzzle array because index's between the two correlate but then splits the answer list up by ',' 
	 * then takes and stores the length of what that array would be like for later.
	 *
	 * @param none
	 * 
	 * @returns none
	 * 
	 */
	
	public void newAnswer() {
		randomIntForAnswer = rand.nextInt(answers.get(randomIntForPuzzle).split(",").length);
	}
	
	/**
	 * 	 
	 * This method is used to import the puzzle array from the puzzle.txt file. The method searches for the file 
	 * where the class is, then passes that file to the file reader and then passes the file reader 
	 * to the BufferedReader which will search through that file and add each line to the array until there is nothing left,
	 * and will finally close the buffered reader. 
	 * 
	 * @param none
	 * 
	 * @returns String
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 */
	
	public String getPuzzle() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("application/puzzle.txt").getFile()));
		// used (above line)code from: https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
		String line = "";
		line = br.readLine();
		while (line != null) {
			puzzle.add(line);
			line = br.readLine();
		}
		br.close();
		randomIntForPuzzle = rand.nextInt(puzzle.size());
		return puzzle.get(randomIntForPuzzle);	
		
	}
	
	/**
	 * 	 
	 * 
	 * This method is used to import the answers from the answer.txt file. Then make a smaller array 
	 * from those answers and return a random one. Searches for the file where the class is, then passes
	 * that file to the file reader and then passes the file reader to the BufferedReader which then searches 
	 * through that file and adds each line to the array until there is nothing left, the buffered reader is then closed.
	 * Afterwards it grabs the same index that the puzzle is on and makes its own tiny array from the split ',' method
	 * and uses the randomIntForAnswer to randomize amongst the smaller list in which the answer is returned.
	 * 
	 * @param none
	 * 
	 * @returns String
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public String getAnswer() throws IOException {
		String[] ans;
		BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("application/answers.txt").getFile()));
		// used (above line)code from: https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
		String line = "";
		line = br.readLine();
	    while (line != null) 
	    {
	        answers.add(line);
	        line = br.readLine();
	    }
	    br.close();
	    ans = answers.get(randomIntForPuzzle).split(",");
        randomIntForAnswer = rand.nextInt(ans.length);
		return ans[randomIntForAnswer];
	}
	
	/**
	 * 	 
	 * This method is used to import the wheel from the wheel.txt file. Searches for the file 
	 * where the class is, then passes that file to the file reader and then passes the file reader 
	 * to the BufferedReader which will then search through that file and add each line to the array 
	 * until there is nothing left and then closes the buffered reader.
	 *
	 * 
	 * @param none
	 * 
	 * @returns ArrayList<String>
	 * 
	 * @throws If an input or output exception occurred
	 * 
	 * 
	 */
	
	public ArrayList<String> getWheel () throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("application/wheel.txt").getFile()));
		// used (above line)code from: https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
		String line = "";
		line = br.readLine();
	    while (line != null) {
	        wheel.add(line);
	        line = br.readLine();
	    }
	    br.close();
		return wheel;
	} 
}
