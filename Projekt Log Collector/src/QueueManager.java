import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 *w¹tek menad¿era kolejki
 */
public class QueueManager extends Thread {
	private Queue<Event> eventsQueue = new ConcurrentLinkedQueue<Event>();
	OutputAdapter outputAdapter;

	public QueueManager(OutputAdapter outputAdapter) {
		this.outputAdapter = outputAdapter;
	}
	/**
	 *akceptowanie zdarzenia
	 */
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
	/**
	 *wysylanie zda¿enia
	 */
	boolean sendEvents() {
		List<Event> batch = new LinkedList<Event>(eventsQueue);
		eventsQueue.clear();

		if(batch.size()>0){
			outputAdapter.storeEvents(batch);
			System.out.println("Wyslano do zapisu " + batch.size() + " eventow");
		}

		return true;
	}
    /**
     *fukcja run w¹tku
     */
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
