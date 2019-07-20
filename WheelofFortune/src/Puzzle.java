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
private int randomInt;
Random rand = new Random();
public void newPuzzle() 
{
	randomInt = rand.nextInt(puzzle.size());
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
	return puzzle.get(randomInt);	
}

public String getAnswer () 
{
	ArrayList<String> ansList = new ArrayList<String>();
	
	return "";
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








