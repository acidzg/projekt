public class ApplicationCore {

	Configuration configuration;

	QueueManager queueManager = new QueueManager();

	public ApplicationCore() {
		
		this.configuration = readFromFileAndCreateConfiguration();

	}

	private Configuration readFromFileAndCreateConfiguration() {

	}

}
