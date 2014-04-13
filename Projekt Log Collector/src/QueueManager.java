import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueManager extends Thread {
	private Queue<Event> eventsQueue = new ConcurrentLinkedQueue<Event>();
	OutputAdapter outputAdapter;

	public QueueManager(OutputAdapter outputAdapter) {
		this.outputAdapter = outputAdapter;
	}

	boolean acceptEvent(Event event) {
		try {
			if (eventsQueue.add(event)) {
				return true;
			}
		} catch (IllegalStateException e) {
			return false;
		}
		return false;
	}

	boolean sendEvents() {
		List<Event> batch = new LinkedList<Event>();
		for (int i = 0; i < eventsQueue.size(); i++) {
			batch.add((Event) eventsQueue.remove());
		}
		/*
		if(batch.size()>0)
			outputAdapter.storeEvents(batch);
		*/
		System.out.println("Wyslano do zapisu " + batch.size() + " eventow");
		return true;
	}

	public void run() {
		while(true) {
			sleep(Application.OUTPUT_SLEEP_TIME);
			sendEvents();
		}
	}

	private void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
