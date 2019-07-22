import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Puzzle extends Game
{
	/*
	 * Possible constructors.
	 * 
	 */
	
	
private ArrayList<String> answers = new ArrayList <String>();
private ArrayList<String> puzzle = new ArrayList <String>();
private ArrayList<String> wheel = new ArrayList <String>();
private int randomIntForPuzzle;
private int randomIntForAnswer;
Random rand = new Random();
public void newPuzzle() 
{
	randomIntForPuzzle = rand.nextInt(puzzle.size());
}
public void newAnswer() 
{
	randomIntForAnswer = rand.nextInt(answers.get(randomIntForPuzzle).split(",").length);
}
public String getPuzzle() throws IOException 
{
	File f = new File(Puzzle.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "//puzzle");
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

public String getAnswer () throws IOException 
{
	String[] ans;
	File f = new File(Puzzle.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "//answer");
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








