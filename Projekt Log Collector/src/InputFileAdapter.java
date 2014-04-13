

public class InputFileAdapter extends Thread implements InputAdapter {
	private Configuration configuration;
	private QueueManager queue;

	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

	public void connectToQueueManager(QueueManager queue) {
		this.queue = queue;
	}

	private void readFile() {
	}

	private Event createEvent() {
		return new Event();
	}

	private void sendEvent() {
		Event event = createEvent();
		queue.acceptEvent(event);
		System.out.println("Dodano nowy event");
	}

	public void run() {
		while(true) {
			try {
				sleep(Application.INPUT_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendEvent();
		}
	}
}
