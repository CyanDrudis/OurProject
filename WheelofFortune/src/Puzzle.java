import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Puzzle extends Game
{
	/*
	 * Possible constructors.
	 * 
	 */
	
	
private String puzzle;
private String answer;
private ArrayList<String> wheel = new ArrayList <String>();

public String getPuzzle() 
{
	return puzzle;	
}

public String getAnswer () 
{
	return answer;
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








