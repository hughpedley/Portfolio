import java.util.ArrayList;
import java.util.Arrays;

public class eventPlanner {
	ArrayList<event> events = new ArrayList<>();
	
	public void addEvent(int time, String event) {
		if(time > 0 && time <= 24) {
			event e = new event(time, event);
			events.add(e);
		} else {
			System.out.println("You did not input a valid time.");
		}
	}
	
	public void deleteEvent(int time) {
		for(int i = 0; i < events.size(); i++) {
			if(events.get(i).time == time) {
				events.remove(i);
				break;
			}
		}
	}
	
	public void deleteEvent(String event) {
		for(int i = 0; i < events.size(); i++) {
			if(events.get(i).event.equalsIgnoreCase(event)) {
				events.remove(i);
				break;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(events.size() * 20);
		event[] array = events.toArray(new event[0]);
		Arrays.sort(array);
		for(event e : array) {
			sb.append(e + "\n");
		}
		
		return sb.toString();
	}
}