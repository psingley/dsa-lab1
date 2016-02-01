package lab;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import  java.util.*;

import junit.framework.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String bowler_log = new String(Files.readAllBytes(Paths.get("/Users/IRON/workspace/Lab 1/Files/bowler.txt")));
		
		Parser prsr = new Parser(bowler_log);
		
		ArrayList<Player> bowlers = prsr.getPlayers();
		
		int indx = 1;
		for(Player bowler : bowlers){
			System.out.println("Player " + indx);
			System.out.println("Score " + bowler.getScore());
			System.out.println("Number of Strikes " + bowler.getNumStrikes());
			System.out.println("Number of Spares " + bowler.getNumSpares());
			System.out.println();
			indx++;
			
		}
	}
}

//TODO//
/*
* Log file read in (Pete)
* Player Bowling rules (Pete)
* 
* Log file parsing (Robert)
* 
* error handling (both)
* construct test cases (both)
* style guide review (both)
*/
//TODO// 


/*
incorrect char
score above 9
missing bowl
Too many bowls in one frame
Too many frames
*/
