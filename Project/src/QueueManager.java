import java.util.LinkedList;
import java.util.Queue;

public class QueueManager {

	private Queue<Event> eventsQueue = new LinkedList<Event>();

	boolean acceptEvent(Event event) {
		try {
			if (eventsQueue.add(event) == true)
				return true;
		} catch (IllegalStateException e) {
			return false;
		}

		return false;
	}

	boolean sendEvents() {

		// wysyla do output adapter
		return true;

	}

}
