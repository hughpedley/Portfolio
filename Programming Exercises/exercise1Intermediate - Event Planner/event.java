/**
 * Class for eventCalendar - holds event object
 * to be inserted into event planner object
 *
 */

public class event implements Comparable<event> {
	public int time;
	public String event;
	public event(int time, String event) {
		this.time = time;
		this.event = event;
	}
	
	/**
	 * returns event string, formatted with its time and then
	 * the name of the event
	 */
	public String toString() {
		return time + ":00 - " + event;
	}
	
	/**
	 * overwrites the compareTo object so that we can see if
	 * there is already an event in place
	 * @param e
	 * @return
	 */
	public int compareTo(event e) {
		if(this.time == e.time) {
			return 0;
		}
		
		return (this.time < e.time ? -1 : 1);
	}
}