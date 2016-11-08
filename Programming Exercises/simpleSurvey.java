/**
 * This is the exercise 1 (easy) from the daily programmer subreddit.
 * The task is simple: get the name, age, and username from the user,
 * and then display it back to them in one formatted statement. Simple stuff.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class simpleSurvey {
	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("What is your name?");
		String name = keyboard.nextLine();
		
		int age;
		while(true){
			try{
				System.out.println("What is your age?");
				age = keyboard.nextInt();
				break;
			} catch(InputMismatchException ime){
				System.out.println("You did not put in an integer age. Please try again.");
				keyboard.nextLine();
			}
		}
		
		keyboard.nextLine();
		System.out.println("What is your reddit username?");
		String username = keyboard.nextLine();
		
		System.out.println("\nYour name is " + name + ", your age is " + age + ", and your username is " + username + ".");
		keyboard.close();
		
		//This is the end of the one assignment.
		//'Bonus points' were 'given' if the program could also log this in a file for later.
		
		File log = new File("log.txt");
		
		try{
			if(!log.exists()){
				log.createNewFile();
			}
			FileWriter fw = new FileWriter(log, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(name + " " + age + " " + username);
			bw.newLine();
			bw.close();
			
		} catch(IOException ioe) {
			System.out.println("Could not access file");
		}
	}
}