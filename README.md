# OurProject

Contributors
CyanMatrix - Harrison Toker,
RoiCB7 - Chris Botuli,
rzwc - Richard Chow,

Our project is based on the game Wheel of Fortune as popularily seen on TV. 

The text based version of the game runs through Runtime.java, while the GUI version 
runs through Main.java.
However, this project was primarily designed in Eclipse, therefore, there will be 
instructions on how to run the project in Eclipse and how to run it through the command console. 
As well the textfiles and the additional files that are downloaded in the OurProject Folder
must be in the same folder in order for the game to function.

In order to run the project in eclipse, the repository must be downloaded and unzipped,
a new java project must be created, right click the src folder, click import, then go 
into file system, click browse, and navigate to the unzipped OurProject folder, then into
the WheelOfFortune folder, click on the src folder, check the src folder, then click finish.
In order to run the text based version of WheelOfFortune, right click Runtime.java, navigate
down to run as, then click run as Java Application.   
In order to run the GUI based version of WheelOfFortune, right click Main.java, navigate
down to run as, then click run as Java Application. 

--------------------------------------------------------How to play----------------------------------------------------------------
  Starting a game:
Start every game by either clicking "New game" or loading a previous game state by clicking "import" then clicking
the menu button(drop down menu) and selecting a previous game. 


  Turns:
Proceed to have whomever's turn it is guess a letter or attempt to solve the puzzle, each empty line in the puzzle is an unguessed      character and the category will give a hint to the answer of the puzzle. Each time a player spins they have a chance to go bankrupt(which will cause you to also lose your turn) or you might just simply lose a turn.
However if a player lands on a spoke with an indicated money value they get that value times the amount of characters they're able to guess in the puzzle, unless that character is a vowel, in which case they have $50 detected per vowel.


  Game completion:
If all of the characters are guessed or the puzzle itself is completely guessed(not case-sensitive) then a new puzzle will generate and after 3 puzzles whomever has the most accumulated points wins!





In order to run the JUnit tests, right click the name of the project, go down to properties,
click on Java Build Path, click Add Library, select JUnit and click Next, select JUnit 4 
from the drop down and click Finish. Next, right click the test file you want to use, 
named ...Test.java, navigate to to run as, click JUnit Test, and the test will run on the 
corresponding class.   

All the .java files should be compiled before running, using the javac command in the 
command console *(*.java)*
In order to run the text based version of WheelOfFortune in the command console, all files 
must be compiled, then the game is run using the command java Runtime. 
In order to run the GUI based version of WheelOfFortune in the command console, all files 
must be compiled, then the game is run using the command java Main. 

As well, in the OurProject folder, there is a sourcesForCPSC233Project, which lists links
of all the sources we've used.

There is also a TestDocument file which details how the JUnit tests were designed, including
the purpose and procedure. 




