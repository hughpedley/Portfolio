import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Create a menu driven program
 * Using the menu drive program allow a user to add/delete items
 * The menu should be based on an events calender where users enter the events by hour
 * No events should be hard-coded.
 */

public class eventCalendar {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		eventPlanner ep = new eventPlanner();
		String input;
		
		System.out.println("Welcome to your event calendar!");
		
		while(true) {
			System.out.println("To add an event, enter add. To remove an event, enter remove. To view your planner, enter view. To quit, enter quit");
			input = keyboard.nextLine();
			
			if(input.equalsIgnoreCase("quit")) {
				System.out.println("Exiting calendar.");
				break;
			} else if(input.equalsIgnoreCase("add")) {
				System.out.println("Please enter an event name.");
				String name = keyboard.nextLine();
				
				System.out.println("Please enter a time for the event.");
				while(true) {
					try {
						int time = keyboard.nextInt();
						ep.addEvent(time, name);
						keyboard.nextLine();
						break;
					} catch(InputMismatchException ime) {
						System.out.println("Please enter a time as an integer from 1-24.");
						keyboard.nextLine();
					}
				}
			} else if(input.equalsIgnoreCase("remove")) {
				System.out.println("Please enter the time of the event you'd like to remove.");
				try {
					int removeTime = keyboard.nextInt();
					ep.deleteEvent(removeTime);
					keyboard.nextLine();
				} catch(InputMismatchException ime) {
					System.out.println("This is not a valid time.");
				}
			} else if(input.equalsIgnoreCase("view")) {
				System.out.println(ep);
			} else {
				System.out.println("You did not enter a valid input.");
			}
		}
		
		//Upon exiting, should save calendar to external file and be able
		//to read it back in upon starting the calendar back up.
		//fortunately or unfortunately, it was not 'assigned' as a part of the description
		keyboard.close();
	}
}