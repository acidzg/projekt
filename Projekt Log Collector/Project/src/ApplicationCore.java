import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationCore {
	private final String configurationFile = "configuration.txt";
	private Configuration configuration;
	private QueueManager queue;
	private InputFileAdapter inputFileAdapter;
	private OutputDatabaseAdapter outputDatabaseAdapter;

	public ApplicationCore() {
		this.configuration = readAndCreateConfiguration();
		this.queue = new QueueManager(this.outputDatabaseAdapter);
		this.queue.start();
		this.inputFileAdapter = new InputFileAdapter();
		this.inputFileAdapter.start();
		this.inputFileAdapter.connectToQueueManager(this.queue);
		this.outputDatabaseAdapter = new OutputDatabaseAdapter();
	}

	private Configuration readAndCreateConfiguration() {
		Properties properties = readConfigurationFromFile();

		return createConfiguration(properties);
	}

	private Properties readConfigurationFromFile() {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("configuration.txt");

			properties.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	private Configuration createConfiguration(Properties properties) {
		Configuration configuration = new Configuration(
				properties.getProperty("inFilePath"),
				properties.getProperty("outDatabaseLogin"),
				properties.getProperty("outDatabasePass"),
				properties.getProperty("outDatabaseAddress"));

		return configuration;
	}
}
