public class inputFileAdapter implements InputAdapter {

	private final Configuration configuration;
	private final QueueManager queueManager;

	public inputFileAdapter() {

	}

	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

	public void connectToQueueManager(QueueManager queue) {
		this.queueManager = queue;
	}

}
