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
 * answers:ArrayList<String>: a List of the answers, each line has multiple answers per category seperated by ','
 * each index in this array correlates with an index in the puzzle array
 * 
 * puzzle:ArrayList<String>: a List of the puzzle categories, one on each index.
 * 
 * wheel:ArrayList<String>: a List of the wheel which includes how much money the player will recieve for each correct
 * consonant.
 * 
 * randomIntForPuzzle:int : a variable that is to be assigned a randomly generated number within the indexs of the puzzle array
 * 
 * randomIntForAnswer:int : a variable that is to be assigned a randomly generated number within the indexs of the answer array
 * 
 *                    
 * NOTES: 
 *********************************************************************************************************************/


public class Puzzle extends Game
{
	private ArrayList<String> answers = new ArrayList <String>();
	private ArrayList<String> puzzle = new ArrayList <String>();
	private ArrayList<String> wheel = new ArrayList <String>();
	private int randomIntForPuzzle;
	private int randomIntForAnswer;
	Random rand = new Random();
	/***************************************************************************************
	 * FUNCTION: generate a new random integer for a new random puzzle
	 * 
	 * DESCRIPTION: passes the size of the puzzle array to the rand method to generate a random number
	 *  and assigns it to RandomIntForPuzzle
	 * 
	 * METHOD: newPuzzle()
	 * 
	 * RETURNS: nothing! it's void!!
	 *
	 * INPUT PARAMETERS:  nothing either!
	 * 
	 *********************************************************************************************/
	public void newPuzzle() 
	{
		randomIntForPuzzle = rand.nextInt(puzzle.size());
	}
	/***************************************************************************************
	 * FUNCTION: generate a random integer for a new random answer
	 * 
	 * DESCRIPTION: passes the same integer created for the puzzle array because index's between the two
	 * correlate but then splits the answer list up by ',' and takes the length of what that array would be like for later
	 * 
	 * METHOD: newAnswer() 
	 * 
	 * RETURNS: nothing! it's void!!
	 *
	 * INPUT PARAMETERS:  nothing either!
	 * 
	 *********************************************************************************************/
	public void newAnswer() 
	{
		randomIntForAnswer = rand.nextInt(answers.get(randomIntForPuzzle).split(",").length);
	}
	/***************************************************************************************
	 * FUNCTION: to import the puzzle array from our own txt file
	 * 
	 * DESCRIPTION: searchs for the file where the class is, then passes that file to the file reader then passes the file reader 
	 * to the BufferedReader then searchs through that file and adds each line to the array till there's nothing left then
	 * closes the buffered reader
	 * 
	 * METHOD: String getPuzzle()
	 * 
	 * RETURNS: a randomized index in the puzzle array
	 *
	 * INPUT PARAMETERS: nothing! 
	 * 
	 *********************************************************************************************/
	public String getPuzzle() throws IOException 
	{
		File f = new File(Puzzle.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "//puzzle.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		line = br.readLine();
		while (line != null) 
		{
			puzzle.add(line);
			line = br.readLine();
		}
		br.close();
		return puzzle.get(randomIntForPuzzle);	
	}
	/***************************************************************************************
	 * FUNCTION: to import the answer array from our own text file, then make a smaller array of those answers and return a random one
	 * 
	 * DESCRIPTION: searchs for the file where the class is, then passes that file to the file reader then passes the file reader 
	 * to the BufferedReader then searchs through that file and adds each line to the array till there's nothing left then
	 * closes the buffered reader
	 * then grabs the same index that the puzzle is on and makes its own tiny array from the split ',' method
	 * and uses the randomIntForAnswer to randomize amongst the smaller list which answer is returned
	 * 
	 * METHOD: String getAnswer()
	 * 
	 * RETURNS: a randomized index in the ans array
	 *
	 * INPUT PARAMETERS: nothing! 
	 * 
	 *********************************************************************************************/
	public String getAnswer () throws IOException 
	{
		String[] ans;
		File f = new File(Puzzle.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "//answer.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		line = br.readLine();
	    while (line != null) 
	    {
	        answers.add(line);
	        line = br.readLine();
	    }
	    br.close();
	    ans = answers.get(randomIntForPuzzle).split(",");
		return ans[randomIntForAnswer];
	}
	/***************************************************************************************
	 * FUNCTION: to import the wheel array from our own text file
	 * 
	 * DESCRIPTION: searchs for the file where the class is, then passes that file to the file reader then passes the file reader 
	 * to the BufferedReader then searchs through that file and adds each line to the array till there's nothing left then
	 * closes the buffered reader
	 * 
	 * METHOD: ArrayList<String> getWheel()
	 * 
	 * RETURNS: The entire wheel array
	 *
	 * INPUT PARAMETERS: nothing! 
	 * 
	 *********************************************************************************************/
	public ArrayList<String> getWheel () throws IOException
	{
		File f = new File(Puzzle.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "//wheel.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		line = br.readLine();
	    while (line != null) 
	    {
	        wheel.add(line);
	        line = br.readLine();
	    }
	    br.close();
		return wheel;
	} 
}








